package com.simia.expert.facade.bonus;

import com.simia.share.common.model.dto.account.referral.ReferralCodeUserDto;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface BonusScoreFacade {

    void addScoreBySignupWithCode(ReferralCodeUserDto referralCodeUserDto);
}
