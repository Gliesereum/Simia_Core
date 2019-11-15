package com.simia.expert.service.expert.impl;

import com.simia.expert.model.entity.expert.ExpertEntity;
import com.simia.expert.model.repository.jpa.expert.ExpertRepository;
import com.simia.expert.service.expert.ExpertService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.exchange.service.account.UserExchangeService;
import com.simia.share.common.model.dto.expert.expert.ExpertDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.simia.share.common.exception.messages.ExpertExceptionMessage.EXPERT_FOR_USER_EXIST;
import static com.simia.share.common.exception.messages.ExpertExceptionMessage.EXPERT_FOR_USER_NOT_EXIST;


@Slf4j
@Service
public class ExpertServiceImpl extends AuditableServiceImpl<ExpertDto, ExpertEntity> implements ExpertService {
	
	private static final Class<ExpertDto> DTO_CLASS = ExpertDto.class;
	private static final Class<ExpertEntity> ENTITY_CLASS = ExpertEntity.class;
	
	private final ExpertRepository expertRepository;
	
	@Autowired
	private UserExchangeService userExchangeService;
	
	@Autowired
	public ExpertServiceImpl(ExpertRepository expertRepository, DefaultConverter defaultConverter) {
		super(expertRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
		this.expertRepository = expertRepository;
	}
	

	@Override
	public ExpertDto getByUserId(UUID userId) {
		ExpertDto result = null;
		if (userId != null) {
			ExpertEntity entity = expertRepository.findByUserIdAndObjectState(userId, ObjectState.ACTIVE);
			result = converter.convert(entity, dtoClass);
		}
		return result;
	}
	
	@Override
	public void deleteByUserId(UUID userId) {
		if (userId != null) {
			ExpertDto existed = getByUserId(userId);
			if (existed == null) {
				throw new ClientException(EXPERT_FOR_USER_NOT_EXIST);
			}
			super.delete(existed);
			userExchangeService.updateExpertStatus(userId, false);
		}
	}
}
