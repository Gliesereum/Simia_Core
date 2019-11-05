package com.simia.expert.model.repository.jpa.agent;

import com.simia.expert.model.entity.agent.AgentEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface AgentRepository extends AuditableRepository<AgentEntity> {

    boolean existsByUserIdAndActiveAndObjectState(UUID userId, Boolean active, ObjectState objectState);

    boolean existsByUserIdAndObjectState(UUID userId, ObjectState objectState);
}
