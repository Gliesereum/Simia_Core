package com.simia.expert.model.repository.jpa.content;

import com.simia.expert.model.entity.content.ContentEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;

import java.util.List;
import java.util.UUID;

public interface ContentRepository extends AuditableRepository<ContentEntity> {

    List<ContentEntity> getAllByAuthorId(UUID authorId);

    List<ContentEntity> getAllByCategoryIdAndObjectState(UUID id, ObjectState state);
}