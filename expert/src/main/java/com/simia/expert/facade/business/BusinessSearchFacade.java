package com.simia.expert.facade.business;

import com.simia.share.common.model.dto.expert.business.group.BusinessGroupDto;
import com.simia.share.common.model.dto.expert.business.search.BusinessGroupSearchDto;

public interface BusinessSearchFacade {
    
    BusinessGroupDto getBusinessGroup(BusinessGroupSearchDto groupSearch);
}
