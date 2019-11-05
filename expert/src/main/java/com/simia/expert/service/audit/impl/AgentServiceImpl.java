package com.simia.expert.service.audit.impl;

import com.simia.expert.model.entity.agent.AgentEntity;
import com.simia.expert.model.repository.jpa.agent.AgentRepository;
import com.simia.expert.service.audit.AgentService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.expert.agent.AgentDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.simia.share.common.exception.messages.KarmaExceptionMessage.AGENT_EXIST;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
public class AgentServiceImpl extends AuditableServiceImpl<AgentDto, AgentEntity> implements AgentService {

    private static final Class<AgentDto> DTO_CLASS = AgentDto.class;
    private static final Class<AgentEntity> ENTITY_CLASS = AgentEntity.class;

    private final AgentRepository agentRepository;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, DefaultConverter defaultConverter) {
        super(agentRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.agentRepository = agentRepository;
    }

    @Override
    public boolean existByUserIdAndActive(UUID userId) {
        boolean result = false;
        if (userId != null) {
            result = agentRepository.existsByUserIdAndActiveAndObjectState(userId, true, ObjectState.ACTIVE);
        }
        return result;
    }

    @Override
    public AgentDto createRequest(UUID userId) {
        AgentDto result = null;
        if (userId != null) {
            if (agentRepository.existsByUserIdAndObjectState(userId, ObjectState.ACTIVE)) {
                throw new ClientException(AGENT_EXIST);
            }
            AgentDto agent = new AgentDto();
            agent.setUserId(userId);
            result = super.create(agent);
        }
        return result;
    }
}
