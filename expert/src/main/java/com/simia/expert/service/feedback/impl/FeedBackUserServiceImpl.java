package com.simia.expert.service.feedback.impl;

import com.simia.expert.facade.business.BusinessPermissionFacade;
import com.simia.expert.model.common.BusinessPermission;
import com.simia.expert.model.entity.feedback.FeedBackUserEntity;
import com.simia.expert.model.repository.jpa.feedback.FeedBackUserRepository;
import com.simia.expert.service.business.BaseBusinessService;
import com.simia.expert.service.business.WorkerService;
import com.simia.expert.service.business.WorkingSpaceService;
import com.simia.expert.service.feedback.FeedBackUserService;
import com.simia.expert.service.record.BaseRecordService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.exchange.service.account.UserExchangeService;
import com.simia.share.common.model.dto.account.user.PublicUserDto;
import com.simia.share.common.model.dto.expert.business.LiteBusinessDto;
import com.simia.share.common.model.dto.expert.business.LiteWorkerDto;
import com.simia.share.common.model.dto.expert.business.LiteWorkingSpaceDto;
import com.simia.share.common.model.dto.expert.feedback.FeedBackSearchDto;
import com.simia.share.common.model.dto.expert.feedback.FeedBackUserDto;
import com.simia.share.common.model.dto.expert.record.BaseRecordDto;
import com.simia.share.common.service.DefaultServiceImpl;
import com.simia.share.common.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.simia.share.common.exception.messages.KarmaExceptionMessage.*;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class FeedBackUserServiceImpl extends DefaultServiceImpl<FeedBackUserDto, FeedBackUserEntity> implements FeedBackUserService {

    private static final Class<FeedBackUserDto> DTO_CLASS = FeedBackUserDto.class;
    private static final Class<FeedBackUserEntity> ENTITY_CLASS = FeedBackUserEntity.class;

    private final FeedBackUserRepository feedBackUserRepository;

    @Autowired
    private BaseRecordService recordService;

    @Autowired
    private BaseBusinessService businessService;

    @Autowired
    private BusinessPermissionFacade businessPermissionFacade;

    @Autowired
    private WorkingSpaceService workingSpaceService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private UserExchangeService userExchangeService;

    @Autowired
    public FeedBackUserServiceImpl(FeedBackUserRepository feedBackUserRepository, DefaultConverter defaultConverter) {
        super(feedBackUserRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.feedBackUserRepository = feedBackUserRepository;
    }

    @Override
    @Transactional
    public void recordFeedback(UUID recordId, String comment, Integer mark) {
        SecurityUtil.checkUserByBanStatus();
        BaseRecordDto record = recordService.getById(recordId);
        if (record == null) {
            throw new ClientException(RECORD_NOT_FOUND);
        }
        if (!record.getClientId().equals(SecurityUtil.getUserId())) {
            throw new ClientException(DONT_HAVE_PERMISSION_TO_ACTION_RECORD);
        }
        if (feedBackUserRepository.findByObjectId(recordId) != null) {
            throw new ClientException(FEEDBACK_FOR_RECORD_EXIST);
        }
        FeedBackUserDto feedBack = new FeedBackUserDto();
        feedBack.setBusinessId(record.getBusinessId());
        feedBack.setClientId(record.getClientId());
        feedBack.setComment(comment);
        feedBack.setMark(mark);
        feedBack.setObjectId(record.getId());
        feedBack.setWorkerId(record.getWorkerId());
        feedBack.setWorkingSpaceId(record.getWorkingSpaceId());
        feedBack.setDateFeedback(LocalDateTime.now());
        feedBack.setDateCreateObject(record.getBegin());
        super.create(feedBack);
    }

    @Override
    public List<FeedBackUserDto> getAllBySearch(FeedBackSearchDto search) {
        checkPermission(search);
        List<FeedBackUserDto> result = null;
        List<FeedBackUserEntity> entities = feedBackUserRepository.getBySearch(search);
        if (CollectionUtils.isNotEmpty(entities)) {
            result = converter.convert(entities, dtoClass);
            setFullModel(result);
        }
        return result;
    }

    @Override
    public FeedBackUserDto getByRecord(UUID recordId) {
        FeedBackUserDto result = null;
        if (recordId != null) {
            FeedBackUserEntity entity = feedBackUserRepository.findByObjectId(recordId);
            if ((entity != null) && !entity.getClientId().equals(SecurityUtil.getUserId())) {
                throw new ClientException(DONT_HAVE_PERMISSION_TO_ACTION_RECORD);
            }
            result = converter.convert(entity, dtoClass);
        }
        return result;
    }

    private void setFullModel(List<FeedBackUserDto> result) {
        Map<UUID, PublicUserDto> clients = userExchangeService.findPublicUserMapByIds(result.stream().map(m->m.getClientId()).collect(Collectors.toSet()));
        Map<UUID, LiteWorkerDto> workers = workerService.getLiteWorkerMapByIds(result.stream().map(m->m.getWorkerId()).collect(Collectors.toSet()));
        Map<UUID, LiteWorkingSpaceDto> workerSpaces = workingSpaceService.getLiteWorkingSpaceMapByIds(result.stream().map(m->m.getWorkingSpaceId()).collect(Collectors.toSet()));
        Map<UUID, LiteBusinessDto> businesses = businessService.getLiteBusinessMapByIds(result.stream().map(m->m.getBusinessId()).collect(Collectors.toSet()));
        result.forEach(f -> {
            f.setWorkerDto(workers.get(f.getWorkerId()));
            f.setWorkingSpaceDto(workerSpaces.get(f.getWorkingSpaceId()));
            f.setClient(clients.get(f.getClientId()));
            f.setBusiness(businesses.get(f.getBusinessId()));
        });
    }

    private void checkPermission(FeedBackSearchDto search) {
        if (CollectionUtils.isEmpty(search.getBusinessIds()) &&
                CollectionUtils.isEmpty(search.getCorporationIds())) {
            throw new ClientException(BUSINESS_IDS_OR_CORPORATION_IDS_SHOUT_BE_FULL);
        }
        Set<UUID> businessIds = new HashSet<>();
        if (CollectionUtils.isNotEmpty(search.getBusinessIds())) {
            businessIds.addAll(search.getBusinessIds());
        }
        if (CollectionUtils.isNotEmpty(search.getCorporationIds())) {
            businessIds.addAll(businessService.getIdsByCorporationIds(search.getCorporationIds()));
        }
        search.setBusinessIds(new ArrayList<>(businessIds));
        businessPermissionFacade.checkPermissionByBusiness(businessIds, BusinessPermission.BUSINESS_ADMINISTRATION);
    }

}
