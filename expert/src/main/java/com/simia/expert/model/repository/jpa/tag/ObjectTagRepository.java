package com.simia.expert.model.repository.jpa.tag;

import com.simia.expert.model.entity.tag.ObjectTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ObjectTagRepository extends JpaRepository<ObjectTagEntity, UUID> {

    ObjectTagEntity getByObjectIdAndAndTagId(UUID objectId, UUID tagId);

    List<ObjectTagEntity> getByObjectId(UUID objectId);

    List<ObjectTagEntity> getByObjectIdIn(List<UUID> objectIds);

    void deleteAllByObjectId(UUID objectId);
}
