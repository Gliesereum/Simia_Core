package com.simia.account.model.repository.jpa.user;

import com.simia.account.model.entity.CorporationSharedOwnershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 */

public interface CorporationSharedOwnershipRepository extends JpaRepository<CorporationSharedOwnershipEntity, UUID> {

    List<CorporationSharedOwnershipEntity> findByCorporationOwnerId(UUID id);

    List<CorporationSharedOwnershipEntity> findByOwnerId(UUID id);

    List<CorporationSharedOwnershipEntity> findAllByOwnerIdIn(List<UUID> userIds);
}
