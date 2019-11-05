package com.simia.expert.service.popular;

import com.simia.expert.model.entity.popular.BusinessPopularEntity;
import com.simia.share.common.model.dto.expert.business.popular.BusinessPopularDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;


public interface BusinessPopularService extends DefaultService<BusinessPopularDto, BusinessPopularEntity> {
    
    void updateBusinessCountAsync(UUID businessId);
    
    void updateBusinessCountAsync(List<UUID> businessIds);
    
    List<BusinessPopularDto> getByBusinessIds(Iterable<UUID> businessIds);
    
    List<BusinessPopularDto> getByBusinessIds(Iterable<UUID> businessIds, int size);
}
