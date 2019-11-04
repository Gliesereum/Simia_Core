package com.simia.account.model.repository.jpa.referral;

import com.simia.account.model.entity.referral.ReferralCodeUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface ReferralCodeUserRepository extends JpaRepository<ReferralCodeUserEntity, UUID> {
}
