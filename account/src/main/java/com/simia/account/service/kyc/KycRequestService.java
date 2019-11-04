package com.simia.account.service.kyc;

import com.simia.account.model.entity.kyc.KycRequestEntity;
import com.simia.share.common.model.dto.account.enumerated.KycRequestType;
import com.simia.share.common.model.dto.account.enumerated.KycStatus;
import com.simia.share.common.model.dto.account.kyc.KycRequestFullModelDto;
import com.simia.share.common.model.dto.account.kyc.KycValuesRequestDto;
import com.simia.share.common.model.dto.account.kyc.KycRequestDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface KycRequestService extends DefaultService<KycRequestDto, KycRequestEntity> {

    KycRequestDto createRequest(KycValuesRequestDto createRequest);

    KycRequestDto updateRequest(KycValuesRequestDto updateRequest);

    boolean openKycRequestExist(UUID objectId, KycRequestType requestType);

    KycRequestDto updateStatus(UUID id, KycStatus newStatus, String comment);

    List<KycRequestDto> getAllByUserId(UUID userId);

    List<KycRequestDto> getAllByUserIdAndStatuses(UUID userId, List<KycStatus> kycStatuses);

    Map<UUID, List<KycRequestDto>> getAllByUserIdsAndStatuses(List<UUID> userIds, List<KycStatus> kycStatuses);

    List<KycRequestFullModelDto> getFullModelAll(List<KycStatus> statuses);

    void delete(KycRequestType requestType, UUID objectId);
}
