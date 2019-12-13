package com.simia.expert.model.repository.jpa.media;

import com.simia.expert.model.entity.media.MediaEntity;
import com.simia.share.common.repository.AuditableRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface MediaRepository extends AuditableRepository<MediaEntity> {

    List<MediaEntity> findByObjectId(UUID objectId);

    MediaEntity findByIdAndObjectId(UUID id, UUID objectId);

    List<MediaEntity> findAllByObjectIdIn(List<UUID> objectIds);
    
    void deleteAllByObjectId(UUID objectId);
}
