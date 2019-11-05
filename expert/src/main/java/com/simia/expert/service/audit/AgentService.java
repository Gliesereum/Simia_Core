package com.simia.expert.service.audit;

import com.simia.expert.model.entity.agent.AgentEntity;
import com.simia.share.common.model.dto.expert.agent.AgentDto;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface AgentService extends AuditableService<AgentDto, AgentEntity> {

    boolean existByUserIdAndActive(UUID userId);

    AgentDto createRequest(UUID userId);
}
