package com.simia.account.model.repository.jpa.kyc;

import com.simia.account.model.entity.kyc.KycRequestEntity;
import com.simia.share.common.model.dto.account.enumerated.KycRequestType;
import com.simia.share.common.model.dto.account.enumerated.KycStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface KycRequestRepository extends JpaRepository<KycRequestEntity, UUID> {

    boolean existsByKycRequestTypeAndObjectIdAndKycStatusNot(KycRequestType requestType, UUID objectId, KycStatus kycStatus);

    List<KycRequestEntity> findAllByObjectIdIn(List<UUID> objectIds);

    List<KycRequestEntity> findAllByObjectIdInAndKycStatusIn(List<UUID> objectIds, List<KycStatus> kycStatuses);

    List<KycRequestEntity> findAllByKycRequestTypeAndObjectId(KycRequestType kycRequestType, UUID objectId);

    List<KycRequestEntity> findAllByKycStatusIn(List<KycStatus> statuses);
}
