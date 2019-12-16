package com.simia.expert.model.repository.jpa.followers;

import com.simia.expert.model.entity.followers.FollowerEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;

import java.util.List;
import java.util.UUID;

public interface FollowerRepository extends AuditableRepository<FollowerEntity> {

    List<FollowerEntity> getAllByExpertIdAndObjectState(UUID id, ObjectState state);

    List<FollowerEntity> getAllByUserIdAndObjectState(UUID id, ObjectState state);

    FollowerEntity getByUserIdAndExpertIdAndObjectState(UUID userId, UUID expertId, ObjectState state);
}