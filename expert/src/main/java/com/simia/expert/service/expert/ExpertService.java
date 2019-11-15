package com.simia.expert.service.expert;

import com.simia.expert.model.entity.expert.ExpertEntity;
import com.simia.share.common.model.dto.expert.expert.ExpertDto;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.UUID;

public interface ExpertService extends AuditableService<ExpertDto, ExpertEntity> {
	
	ExpertDto createExpert(ExpertDto expert, UUID userId);
	
	ExpertDto updateExpert(ExpertDto expert, UUID userId);
	
	boolean existByUserId(UUID userId);
	
	ExpertDto getByUserId(UUID userId);
	
	void deleteByUserId(UUID userId);
}
