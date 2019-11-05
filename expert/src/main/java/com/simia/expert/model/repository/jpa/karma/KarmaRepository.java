package com.simia.expert.model.repository.jpa.karma;

import com.simia.expert.model.entity.karma.KarmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface KarmaRepository extends JpaRepository<KarmaEntity, UUID> {

    KarmaEntity findByObjectId(UUID objectId);
}
