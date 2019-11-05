package com.simia.expert.model.repository.jpa.pincode;

import com.simia.expert.model.entity.pincode.UserPinCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface UserPinCodeRepository extends JpaRepository<UserPinCodeEntity, UUID> {

    UserPinCodeEntity findByUserId(UUID userId);
}
