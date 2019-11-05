package com.simia.expert.service.record;

import com.simia.expert.model.entity.record.BaseRecordEntity;
import com.simia.share.common.model.dto.expert.enumerated.StatusPay;
import com.simia.share.common.model.dto.expert.enumerated.StatusProcess;
import com.simia.share.common.model.dto.expert.enumerated.StatusRecord;
import com.simia.share.common.model.dto.expert.record.BaseRecordDto;
import com.simia.share.common.model.dto.expert.record.RecordFreeTime;
import com.simia.share.common.model.dto.expert.record.RecordPaymentInfoDto;
import com.simia.share.common.model.dto.expert.record.RequestLiteRecordDto;
import com.simia.share.common.model.dto.expert.record.search.BusinessRecordSearchDto;
import com.simia.share.common.model.dto.expert.record.search.BusinessRecordSearchPageableDto;
import com.simia.share.common.model.dto.expert.record.search.ClientRecordSearchDto;
import com.simia.share.common.service.DefaultService;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 * @since 12/7/18
 */
public interface BaseRecordService extends DefaultService<BaseRecordDto, BaseRecordEntity> {

    List<BaseRecordDto> getByParamsForClient(ClientRecordSearchDto search);

    List<BaseRecordDto> getByBusinessIdAndStatusRecord(UUID businessId, StatusRecord status, LocalDateTime from, LocalDateTime to);

    List<BaseRecordDto> getByBusinessIdAndStatusRecordNotificationSend(UUID businessId, StatusRecord status, LocalDateTime from, LocalDateTime to, boolean notificationSend);

    List<BaseRecordDto> getByTimeBetween(LocalDateTime from, Integer minutesFrom, Integer minutesTo, StatusRecord status, boolean notificationSend);

    List<BaseRecordDto> getByFinishTimeInPast(LocalDateTime from, StatusProcess status);

    Page<BaseRecordDto> getByParamsForBusiness(BusinessRecordSearchPageableDto search);

    RecordPaymentInfoDto getPaymentInfoForBusiness(BusinessRecordSearchDto search);

    Long getPriceSum(BusinessRecordSearchDto search);

    BaseRecordDto updateStatusProgress(UUID idRecord, StatusProcess status);

    BaseRecordDto canceledRecord(UUID idRecord, String message);

    BaseRecordDto updateRecordTime(UUID idRecord, Long beginTime);

    BaseRecordDto getFreeTimeForRecord(BaseRecordDto dto, Boolean isCustom);

    BaseRecordDto superCreateRecord(BaseRecordDto dto);

    Page<BaseRecordDto> getAllByUser(Integer page, Integer size);

    BaseRecordDto createForBusiness(BaseRecordDto dto, Boolean isCustom);

    BaseRecordDto getFullModelById(UUID id);

    BaseRecordDto getFullModelByIdWithPermission(UUID id);

    BaseRecordDto updateStatusPay(UUID idRecord, StatusPay status);

    List<BaseRecordDto> convertToLiteRecordDto(List<BaseRecordEntity> entities);

    void setFullModelRecord(List<BaseRecordDto> list);

    void setNotificationSend(UUID recordId);

    Map<UUID, Set<RecordFreeTime>> getFreeTimes(UUID businessId, UUID workerId, Long from);

    Page<BaseRecordDto> getByClientForCorporation(List<UUID> corporationIds, UUID clientId, Integer page, Integer size);

    long countByStatusRecord(StatusRecord statusRecord);

    long countBusyWorker(LocalDateTime time, StatusRecord status);

    BaseRecordDto createLite(RequestLiteRecordDto dto);
}
