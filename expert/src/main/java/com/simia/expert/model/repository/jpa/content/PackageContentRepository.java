package com.simia.expert.model.repository.jpa.content;

import com.simia.expert.model.entity.content.PackageContentEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;

import java.util.List;
import java.util.UUID;

public interface PackageContentRepository extends AuditableRepository<PackageContentEntity> {

    List<PackageContentEntity> getAllByPackageIdAndObjectState(UUID id, ObjectState state);
}