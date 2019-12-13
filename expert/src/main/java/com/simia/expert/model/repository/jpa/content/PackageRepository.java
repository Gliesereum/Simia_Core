package com.simia.expert.model.repository.jpa.content;

import com.simia.expert.model.entity.content.PackageEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;

import java.util.List;
import java.util.UUID;

public interface PackageRepository extends AuditableRepository<PackageEntity> {

    List<PackageEntity> getAllByAuthorIdAndObjectState(UUID authorId, ObjectState state);

    boolean existsByIdAndAuthorIdAndObjectState(UUID id, UUID authorId, ObjectState state);

    List<PackageEntity> getAllByCategoryIdAndObjectState(UUID id, ObjectState state);
}