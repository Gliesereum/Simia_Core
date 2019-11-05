package com.simia.expert.service.business.impl;

import com.simia.expert.facade.business.BusinessPermissionFacade;
import com.simia.expert.model.common.BusinessPermission;
import com.simia.expert.model.entity.business.WorkTimeEntity;
import com.simia.expert.model.repository.jpa.business.WorkTimeRepository;
import com.simia.expert.service.business.BaseBusinessService;
import com.simia.expert.service.business.WorkTimeService;
import com.simia.expert.service.business.WorkerService;
import com.simia.expert.service.es.BusinessEsService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.AdditionalClientException;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.expert.business.*;
import com.simia.share.common.model.dto.expert.enumerated.WorkTimeType;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

import static com.simia.share.common.exception.messages.CommonExceptionMessage.TIME_FROM_CAN_NOT_BE_AFTER_TO_TIME;
import static com.simia.share.common.exception.messages.KarmaExceptionMessage.*;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class WorkTimeServiceImpl extends DefaultServiceImpl<WorkTimeDto, WorkTimeEntity> implements WorkTimeService {

    private static final Class<WorkTimeDto> DTO_CLASS = WorkTimeDto.class;
    private static final Class<WorkTimeEntity> ENTITY_CLASS = WorkTimeEntity.class;

    private final WorkTimeRepository workTimeRepository;

    @Autowired
    private BusinessPermissionFacade businessPermissionFacade;

    @Autowired
    private BusinessEsService businessEsService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private BaseBusinessService businessService;

    public WorkTimeServiceImpl(WorkTimeRepository workTimeRepository, DefaultConverter defaultConverter) {
        super(workTimeRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.workTimeRepository = workTimeRepository;
    }

    @Override
    public List<WorkTimeDto> getByObjectId(UUID objectId) {
        List<WorkTimeDto> result = null;
        if (objectId != null) {
            List<WorkTimeEntity> entities = workTimeRepository.findByObjectId(objectId);
            result = converter.convert(entities, dtoClass);
        }
        return result;
    }

    @Override
    @Transactional
    public WorkTimeDto create(WorkTimeDto dto) {
        WorkTimeDto result = null;
        if (dto != null) {
            checkOpportunityForCreateWorkTime(Arrays.asList(dto));
            checkDayExist(dto);
            checkPermission(dto.getType(), dto.getObjectId());
            result = super.create(dto);
            indexAsync(dto.getType(), result.getObjectId());
        }
        return result;
    }

    @Override
    @Transactional
    public List<WorkTimeDto> create(List<WorkTimeDto> list) {
        List<WorkTimeDto> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            checkOpportunityForCreateWorkTime(list);
            WorkTimeDto dto = list.get(0);
            checkPermission(dto.getType(), dto.getObjectId());
            list.forEach(this::checkDayExist);
            result = super.create(list);
            indexAsync(dto.getType(), dto.getObjectId());
        }
        return result;
    }

    @Override
    @Transactional
    public List<WorkTimeDto> update(List<WorkTimeDto> list) {
        List<WorkTimeDto> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            checkOpportunityForCreateWorkTime(list);
            WorkTimeDto dto = list.get(0);
            checkPermission(dto.getType(), dto.getObjectId());
            result = super.update(list);
            indexAsync(dto.getType(), dto.getObjectId());
        }
        return result;
    }

    @Override
    public WorkTimeDto update(WorkTimeDto dto) {
        WorkTimeDto result = null;
        if (dto != null) {
            checkOpportunityForCreateWorkTime(Arrays.asList(dto));
            WorkTimeDto time = getById(dto.getId());
            if (time == null) {
                throw new ClientException(WORKING_TIME_NOT_FOUND);
            }
            if (!dto.getDayOfWeek().equals(time.getDayOfWeek())) {
                checkDayExist(dto);
            }
            checkPermission(dto.getType(), dto.getObjectId());
            result = super.update(dto);
            indexAsync(dto.getType(), result.getObjectId());
        }
        return result;
    }

    @Override
    public void delete(UUID id, UUID businessCategoryId, WorkTimeType type) {
        if ((id != null) && (businessCategoryId != null)) {
            Optional<WorkTimeEntity> entity = repository.findById(id);
            entity.ifPresent(i -> {
                checkPermission(type, i.getObjectId());
                repository.delete(i);
                indexAsync(type, i.getObjectId());
            });
        }
    }

    @Override
    public void deleteByObjectId(UUID id) {
        workTimeRepository.deleteAllByObjectId(id);
    }

    @Override
    @Transactional
    public void checkWorkTimesByBusyTime(List<WorkTimeDto> list, WorkerDto worker) {
        if (CollectionUtils.isNotEmpty(list) && worker != null) {
            checkWorkTimesCorrect(list);
            BaseBusinessDto business = businessService.getByIdAndLock(worker.getBusinessId());
            if (business == null) {
                throw new ClientException(BUSINESS_NOT_FOUND);
            }
            if (CollectionUtils.isEmpty(business.getWorkTimes())) {
                throw new ClientException(BUSINESS_NOT_HAVE_WORKING_TIME);
            }
            Map<DayOfWeek, WorkTimeDto> mapBusinessWorkTime = business.getWorkTimes().stream().collect(Collectors.toMap(WorkTimeDto::getDayOfWeek, w -> w));
            Map<String, Object> mapBusinessDayException = new HashMap<>();
            list.forEach(f -> {
                WorkTimeDto businessTimeWork = mapBusinessWorkTime.get(f.getDayOfWeek());
                if (businessTimeWork == null || !businessTimeWork.getIsWork() && f.getIsWork()) {
                    mapBusinessDayException.put(f.getDayOfWeek().name(), false);
                }
            });
            if (MapUtils.isNotEmpty(mapBusinessDayException)) {
                throw new AdditionalClientException(BUSINESS_NOT_WORK_THIS_DAY, mapBusinessDayException);
            }
            Map<String, Object> mapBusinessTimeException = new HashMap<>();
            list.forEach(f -> {
                WorkTimeDto businessTimeWork = mapBusinessWorkTime.get(f.getDayOfWeek());
                if (f.getIsWork() && (businessTimeWork.getFrom().isAfter(f.getFrom()) ||
                        businessTimeWork.getTo().isBefore(f.getTo()))) {
                    mapBusinessTimeException.put(f.getDayOfWeek().name(),
                            Map.of("from", businessTimeWork.getFrom().toString(), "to", businessTimeWork.getTo().toString()));
                }
            });
            if (MapUtils.isNotEmpty(mapBusinessTimeException)) {
                throw new AdditionalClientException(BUSINESS_TIME_ONLY, mapBusinessTimeException);
            }
            if (worker.getWorkingSpaceId() != null) {
                List<WorkerDto> workers = workerService.getByWorkingSpaceId(worker.getWorkingSpaceId());
                if (CollectionUtils.isNotEmpty(workers)) {
                    workers = workers.stream().filter(filter -> !filter.getId().equals(worker.getId())).collect(Collectors.toList());
                    if (workers.size() > 0) {
                        workers.forEach(otherWorker -> {
                            List<WorkTimeDto> workTimes = otherWorker.getWorkTimes();
                            if (CollectionUtils.isNotEmpty(workTimes)) {
                                Map<DayOfWeek, WorkTimeDto> mapWorkerWorkTime = workTimes.stream().collect(Collectors.toMap(WorkTimeDto::getDayOfWeek, w -> w));
                                Map<String, Object> mapWorkerTimeException = new HashMap<>();
                                list.forEach(f -> {
                                    WorkTimeDto workerTimeWork = mapWorkerWorkTime.get(f.getDayOfWeek());
                                    if ((f.getIsWork() && workerTimeWork.getIsWork()) &&
                                            ((f.getFrom().isBefore(workerTimeWork.getFrom()) && f.getTo().isAfter(workerTimeWork.getFrom())) ||
                                                    (f.getTo().isAfter(workerTimeWork.getTo()) && f.getFrom().isBefore(workerTimeWork.getTo())) ||
                                                    (f.getFrom().isAfter(workerTimeWork.getFrom()) && f.getTo().isBefore(workerTimeWork.getTo())) ||
                                                    (f.getFrom().isBefore(workerTimeWork.getFrom()) && f.getTo().isAfter(workerTimeWork.getTo())) ||
                                                    (f.getTo().equals(workerTimeWork.getTo()) || f.getFrom().equals(workerTimeWork.getFrom())))) {
                                        mapWorkerTimeException.put(f.getDayOfWeek().name(),
                                                Map.of("to", workerTimeWork.getTo().toString(), "from", workerTimeWork.getFrom().toString()));
                                    }
                                });
                                if (MapUtils.isNotEmpty(mapWorkerTimeException)) {
                                    throw new AdditionalClientException(WORKING_TIME_BUSY, mapWorkerTimeException);
                                }
                            }
                        });
                    }
                }
            }
        }
    }

    private void checkPermission(WorkTimeType type, UUID objectId) {
        switch (type) {
            case WORKER: {
                LiteWorkerDto worker = workerService.getLiteWorkerById(objectId);
                if (worker != null) {
                    businessPermissionFacade.checkPermissionByBusiness(worker.getBusinessId(), BusinessPermission.BUSINESS_ADMINISTRATION);
                }
                break;
            }
            case BUSINESS: {
                businessPermissionFacade.checkPermissionByBusiness(objectId, BusinessPermission.BUSINESS_ADMINISTRATION);
                break;
            }
        }
    }

    private void indexAsync(WorkTimeType type, UUID objectId) {
        switch (type) {
            case WORKER: {
                break;
            }
            case BUSINESS: {
                businessEsService.indexAsync(objectId);
                break;
            }
        }
    }

    private void checkDayExist(WorkTimeDto dto) {
        if (workTimeRepository.existsByObjectIdAndDayOfWeek(dto.getObjectId(), dto.getDayOfWeek())) {
            throw new ClientException(WORKING_TIME_EXIST_IN_THIS_BUSINESS);
        }
    }

    private void checkOpportunityForCreateWorkTime(List<WorkTimeDto> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            WorkTimeDto dto = list.get(0);
            if (dto.getType() != null) {
                switch (dto.getType()) {
                    case BUSINESS: {
                        checkWorkTimesCorrect(list);
                        break;
                    }
                    case WORKER: {
                        WorkerDto worker = workerService.getById(dto.getObjectId());
                        if (worker == null) {
                            throw new ClientException(WORKER_NOT_FOUND);
                        }
                        checkWorkTimesByBusyTime(list, worker);
                        break;
                    }
                }
            }
        }
    }

    private void checkWorkTimesCorrect(List<WorkTimeDto> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            if (list.stream().anyMatch(i -> !i.getObjectId().equals(list.get(0).getObjectId()))) {
                throw new ClientException(ALL_OBJECT_ID_NOT_EQUALS);
            }
            if (list.get(0).getType() == null) {
                throw new ClientException(TYPE_IS_NULL);
            }
            if (list.stream().anyMatch(i -> !i.getType().equals(list.get(0).getType()))) {
                throw new ClientException(ALL_TYPE_NOT_EQUALS);
            }
            if (list.stream().anyMatch(i -> i.getFrom().isAfter(i.getTo()))) {
                throw new ClientException(TIME_FROM_CAN_NOT_BE_AFTER_TO_TIME);
            }
        }
    }
}
