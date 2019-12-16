package com.simia.expert.model.repository.jpa.expert;

import com.simia.expert.model.entity.expert.ExpertEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;

import java.util.UUID;

public interface ExpertRepository extends AuditableRepository<ExpertEntity> {
	
	ExpertEntity findByUserIdAndObjectState(UUID userId, ObjectState objectState);
	
	boolean existsByUserIdAndObjectState(UUID userId, ObjectState objectState);
}
