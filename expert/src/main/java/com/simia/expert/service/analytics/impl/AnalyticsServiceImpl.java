package com.simia.expert.service.analytics.impl;

import com.simia.expert.facade.business.BusinessPermissionFacade;
import com.simia.expert.model.common.BusinessPermission;
import com.simia.expert.model.entity.record.BaseRecordEntity;
import com.simia.expert.model.repository.jpa.record.BaseRecordRepository;
import com.simia.expert.service.analytics.AnalyticsService;
import com.simia.expert.service.business.WorkerService;
import com.simia.expert.service.business.WorkingSpaceService;
import com.simia.expert.service.record.BaseRecordService;
import com.simia.expert.service.service.PackageService;
import com.simia.expert.service.service.ServicePriceService;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.expert.analytics.*;
import com.simia.share.common.model.dto.expert.business.LiteWorkerDto;
import com.simia.share.common.model.dto.expert.business.LiteWorkingSpaceDto;
import com.simia.share.common.model.dto.expert.enumerated.StatusRecord;
import com.simia.share.common.model.dto.expert.record.BaseRecordDto;
import com.simia.share.common.model.dto.expert.service.LitePackageDto;
import com.simia.share.common.model.dto.expert.service.LiteServicePriceDto;
import com.simia.share.common.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static com.simia.share.common.exception.messages.CommonExceptionMessage.BODY_INVALID;
import static com.simia.share.common.exception.messages.KarmaExceptionMessage.BUSINESS_ID_EMPTY;
import static com.simia.share.common.exception.messages.KarmaExceptionMessage.TIME_IS_NOT_CORRECT;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private BaseRecordRepository baseRecordRepository;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private BaseRecordService baseRecordService;

    @Autowired
    private ServicePriceService servicePriceService;

    @Autowired
    private PackageService packageService;

    @Autowired
    private WorkingSpaceService workingSpaceService;

    @Autowired
    private BusinessPermissionFacade businessPermissionFacade;

    @Override
    //TODO: check permission
    public AnalyticDto getAnalyticByFilter(AnalyticFilterDto filter) {
        AnalyticDto result = new AnalyticDto();
        List<BaseRecordDto> records = getRecords(filter);
        FullAnalyticDto dataToAnalytic = getDataToAnalytic(records, filter);
        mapToAnalyticModel(result, dataToAnalytic.getPackages(), AnalyticDto::setPackages);
        mapToAnalyticModel(result, dataToAnalytic.getServices(), AnalyticDto::setServices);
        mapToAnalyticModel(result, dataToAnalytic.getWorkers(), AnalyticDto::setWorkers);
        mapToAnalyticModel(result, dataToAnalytic.getWorkingSpaces(), AnalyticDto::setWorkingSpaces);

        return result;
    }

    @Override
    //TODO: check permission
    public CountAnalyticDto getCountAnalyticByFilter(AnalyticFilterDto filter, boolean includeRecord) {
        CountAnalyticDto result = new CountAnalyticDto();
        List<BaseRecordDto> records = getRecords(filter);
        FullAnalyticDto dataToAnalytic = getDataToAnalytic(records, filter);
        mapToCountAnalyticModel(result, dataToAnalytic.getPackages(), CountAnalyticDto::setPackages, includeRecord);
        mapToCountAnalyticModel(result, dataToAnalytic.getServices(), CountAnalyticDto::setServices, includeRecord);
        mapToCountAnalyticModel(result, dataToAnalytic.getWorkers(), CountAnalyticDto::setWorkers, includeRecord);
        mapToCountAnalyticModel(result, dataToAnalytic.getWorkingSpaces(), CountAnalyticDto::setWorkingSpaces, includeRecord);

        return result;
    }

    private <T extends DefaultDto> void mapToCountAnalyticModel(CountAnalyticDto target, Map<String, FullAnalyticItemDto<T>> source,
                                                                BiConsumer<CountAnalyticDto, List<CountAnalyticItemDto<T>>> valueMapper,
                                                                boolean includeRecord) {
        if (MapUtils.isNotEmpty(source)) {
            long recordCountForCategory = source.values().stream().mapToInt(i -> i.getRecords().size()).sum();
            List<CountAnalyticItemDto<T>> list = source.entrySet().stream().map(i -> {
                CountAnalyticItemDto<T> item = new CountAnalyticItemDto<>();
                FullAnalyticItemDto<T> value = i.getValue();
                item.setName(i.getKey());
                item.setId(value.getObject().getId());
                item.setObject(value.getObject());
                if (includeRecord) {
                    item.setRecords(value.getRecords());
                }
                int count = value.getRecords().size();
                item.setRecordCount(count);
                if (count > 0) {
                    item.setUsagePercent(((double) count / recordCountForCategory) * 100);
                } else {
                    item.setUsagePercent(0.0);
                }
                return item;
            }).collect(Collectors.toList());
            valueMapper.accept(target, list);
        }
    }

    private <T extends DefaultDto> void mapToAnalyticModel(AnalyticDto target, Map<String, FullAnalyticItemDto<T>> source,
                                                           BiConsumer<AnalyticDto, Map<String, Set<BaseRecordDto>>> valueMapper) {
        if (MapUtils.isNotEmpty(source)) {
            Map<String, Set<BaseRecordDto>> value = source.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, i -> i.getValue().getRecords()));
            valueMapper.accept(target, value);
        }
    }

    private List<BaseRecordDto> getRecords(AnalyticFilterDto filter) {
        List<BaseRecordDto> records = null;
        checkFilter(filter);
        List<BaseRecordEntity> entities = baseRecordRepository.getRecordsByFilter(filter);
        if (CollectionUtils.isNotEmpty(entities)) {
            records = baseRecordService.convertToLiteRecordDto(entities);
        }
        return records;
    }

    private FullAnalyticDto getDataToAnalytic(List<BaseRecordDto> records, AnalyticFilterDto filter) {
        FullAnalyticDto result = new FullAnalyticDto();

        Map<UUID, Set<BaseRecordDto>> services = new HashMap<>();
        Map<UUID, Set<BaseRecordDto>> packages = new HashMap<>();
        Map<UUID, Set<BaseRecordDto>> workers = new HashMap<>();
        Map<UUID, Set<BaseRecordDto>> spaces = new HashMap<>();

        if (CollectionUtils.isNotEmpty(records)) {
            records.forEach(record -> {
                if (CollectionUtils.isNotEmpty(record.getServicesIds())) {
                    record.getServicesIds().forEach(s -> putToMapIfKeyNotNull(services, s, record));
                }
                putToMapIfKeyNotNull(packages, record.getPackageId(), record);
                putToMapIfKeyNotNull(workers, record.getWorkerId(), record);
                putToMapIfKeyNotNull(spaces, record.getWorkingSpaceId(), record);
            });
        }

        List<LitePackageDto> businessPackages = packageService.getLitePackageByBusinessId(filter.getBusinessId());
        if (CollectionUtils.isNotEmpty(businessPackages)) {
            businessPackages.forEach(f -> result.getPackages().put(f.getName(), getAnalyticItem(f, packages.get(f.getId()))));
            result.setPackages(sortMap(result.getPackages()));
        }

        List<LiteServicePriceDto> businessServices = servicePriceService.getLiteServicePriceByBusinessId(filter.getBusinessId());
        if (CollectionUtils.isNotEmpty(businessServices)) {
            businessServices.forEach(f -> result.getServices().put(f.getName(), getAnalyticItem(f, services.get(f.getId()))));
            result.setServices(sortMap(result.getServices()));
        }

        List<LiteWorkingSpaceDto> businessWorkingSpace = workingSpaceService.getLiteWorkingSpaceByBusinessId(filter.getBusinessId());
        if (CollectionUtils.isNotEmpty(businessWorkingSpace)) {
            businessWorkingSpace.forEach(f -> result.getWorkingSpaces().put(f.getIndexNumber().toString(), getAnalyticItem(f, spaces.get(f.getId()))));
            result.setWorkingSpaces(sortMap(result.getWorkingSpaces()));
        }

        List<LiteWorkerDto> businessWorkers = workerService.getLiteWorkerByBusinessId(filter.getBusinessId());
        if (CollectionUtils.isNotEmpty(businessWorkers)) {
            businessWorkers.forEach(f -> {
                if (f.getUser() != null) {
                    result.getWorkers().put(f.getUser().getFirstName().concat(" ").concat(f.getUser().getLastName()), getAnalyticItem(f, workers.get(f.getId())));
                }
            });
            result.setWorkers(sortMap(result.getWorkers()));
        }
        return result;
    }

    private <T extends DefaultDto> FullAnalyticItemDto<T> getAnalyticItem(T object, Set<BaseRecordDto> records) {
        FullAnalyticItemDto<T> analyticItem = new FullAnalyticItemDto<>();
        analyticItem.setObject(object);
        analyticItem.setRecords((records != null) ? records : new HashSet<>());
        return analyticItem;
    }

    private void checkFilter(AnalyticFilterDto filter) {
        if (filter == null) {
            throw new ClientException(BODY_INVALID);
        }
        SecurityUtil.checkUserByBanStatus();
        if (filter.getBusinessId() == null) {
            throw new ClientException(BUSINESS_ID_EMPTY);
        }
        businessPermissionFacade.checkPermissionByBusiness(filter.getBusinessId(), BusinessPermission.BUSINESS_ADMINISTRATION);
        if (filter.getFrom() != null && filter.getTo() != null && filter.getFrom().isAfter(filter.getTo())) {
            throw new ClientException(TIME_IS_NOT_CORRECT);
        }
        if (CollectionUtils.isEmpty(filter.getStatuses())) {
            filter.setStatuses(Arrays.asList(StatusRecord.COMPLETED));
        }
    }

    private <T extends DefaultDto> Map<String, FullAnalyticItemDto<T>> sortMap(Map<String, FullAnalyticItemDto<T>> map) {
        if (MapUtils.isNotEmpty(map)) {
            return map.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(i -> i.getRecords().size())))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        }
        return map;
    }

    private Set<BaseRecordDto> getNewRecordTreeSet() {
        return new TreeSet<>(Comparator.comparing(BaseRecordDto::getBegin));
    }

    private void putToMapIfKeyNotNull(Map<UUID, Set<BaseRecordDto>> map, UUID key, BaseRecordDto record) {
        if (key != null) {
            Set<BaseRecordDto> value = map.getOrDefault(key, getNewRecordTreeSet());
            value.add(record);
            map.put(key, value);
        }
    }
}
