package com.simia.account.service.referral;

import com.simia.account.model.entity.referral.ReferralCodeUserEntity;
import com.simia.share.common.model.dto.account.referral.ReferralCodeUserDto;
import com.simia.share.common.service.DefaultService;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface ReferralCodeUserService extends DefaultService<ReferralCodeUserDto, ReferralCodeUserEntity> {

    ReferralCodeUserDto create(UUID userId, UUID referralCodeId, UUID referrerId);
}
