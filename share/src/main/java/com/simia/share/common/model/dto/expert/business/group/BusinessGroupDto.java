package com.simia.share.common.model.dto.expert.business.group;


import com.simia.share.common.model.dto.expert.business.group.enumerated.BusinessGroupBy;
import com.simia.share.common.model.dto.expert.business.document.BusinessDocumentDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class BusinessGroupDto {
    
    private Map<BusinessGroupBy, List<BusinessGroupListItemDto>> groups;
    
    private Page<BusinessDocumentDto> page;
    
    private Map<String, BusinessGroupTagDto> tagGroups;
}
