package com.simia.expert.model.repository.jpa.record;

import com.simia.expert.model.entity.record.BaseRecordEntity;
import com.simia.share.common.model.dto.expert.enumerated.StatusProcess;
import com.simia.share.common.model.dto.expert.enumerated.StatusRecord;
import com.simia.share.common.repository.refreshable.RefreshableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 * @since 12/7/18x
 */
public interface BaseRecordRepository extends JpaRepository<BaseRecordEntity, UUID>, RefreshableRepository, BaseRecordAnalyticRepository, BaseRecordSearchRepository  {

    List<BaseRecordEntity> findByStatusRecordInAndStatusProcessInAndBusinessIdInAndBeginBetweenOrderByBegin(
            List<StatusRecord> status, List<StatusProcess> processes, List<UUID> businessIds, LocalDateTime from, LocalDateTime to);

    List<BaseRecordEntity> findByStatusRecordInAndStatusProcessInAndTargetIdInAndBeginBetweenOrderByBeginDesc(
            List<StatusRecord> status, List<StatusProcess> processes, List<UUID> targetIds, LocalDateTime from, LocalDateTime to);

    List<BaseRecordEntity> findByBusinessIdAndStatusRecordAndBeginBetween(UUID businessId, StatusRecord status, LocalDateTime from, LocalDateTime to);

    List<BaseRecordEntity> findByBusinessIdAndStatusRecordInAndBeginBetween(UUID businessId, List<StatusRecord> statuses, LocalDateTime from, LocalDateTime to);

    List<BaseRecordEntity> findByBusinessIdAndStatusRecordAndBeginBetweenAndNotificationSend(UUID businessId, StatusRecord status, LocalDateTime from, LocalDateTime to, boolean notificationSend);

    Page<BaseRecordEntity> findAllByClientId(UUID id, Pageable pageable);

    Page<BaseRecordEntity> findAllByBusinessIdInAndClientIdOrderByBeginDesc(List<UUID> businessIds, UUID clientId, Pageable pageable);

    long countByBusinessIdAndBeginBetween(UUID businessId, LocalDateTime from, LocalDateTime to);

    @Query("SELECT DISTINCT b.clientId FROM BaseRecordEntity b WHERE b.businessId IN :ids")
    List<UUID> getCustomerIdsByBusinessIds(@Param("ids") List<UUID> ids);

    long countByStatusRecord(StatusRecord statusRecord);
}
