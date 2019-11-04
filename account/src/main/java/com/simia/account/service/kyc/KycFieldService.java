package com.simia.account.service.kyc;

import com.simia.account.model.entity.kyc.KycFieldEntity;
import com.simia.share.common.model.dto.account.enumerated.KycRequestType;
import com.simia.share.common.model.dto.account.kyc.KycFieldDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface KycFieldService extends DefaultService<KycFieldDto, KycFieldEntity> {

    List<KycFieldDto> getByType(KycRequestType requestType);
}
