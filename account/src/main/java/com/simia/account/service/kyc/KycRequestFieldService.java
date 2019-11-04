package com.simia.account.service.kyc;

import com.simia.account.model.entity.kyc.KycRequestFieldEntity;
import com.simia.share.common.model.dto.account.kyc.KycRequestFieldDto;
import com.simia.share.common.service.DefaultService;

import java.util.UUID;


/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface KycRequestFieldService extends DefaultService<KycRequestFieldDto, KycRequestFieldEntity> {

    void deleteAllByKycRequestId(UUID kycRequestId);
}
