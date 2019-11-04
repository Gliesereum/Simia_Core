package com.simia.share.common.model.dto.karma.business.group;

import com.simia.share.common.model.dto.karma.business.document.BusinessDocumentDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusinessGroupListItemDto {
    
    private BusinessDocumentDto business;
    
    private Object object;
    
    private Long count;
}
