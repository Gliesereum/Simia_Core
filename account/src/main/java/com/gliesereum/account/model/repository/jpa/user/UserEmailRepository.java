package com.gliesereum.account.model.repository.jpa.user;

import com.gliesereum.account.model.entity.UserEmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 */

public interface UserEmailRepository extends JpaRepository<UserEmailEntity, UUID> {

    void deleteUserEmailEntityByUserId(UUID id);

    UserEmailEntity getUserEmailEntityByEmail(String email);

    boolean existsUserEmailEntityByEmail(String email);

    boolean existsByEmailAndUserIdNot(String email, UUID userId);

    UserEmailEntity getByUserId(UUID id);

    List<UserEmailEntity> findByUserIdIn(List<UUID> ids);
}
