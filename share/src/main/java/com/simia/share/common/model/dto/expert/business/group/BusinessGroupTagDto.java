package com.simia.share.common.model.dto.expert.business.group;

import com.simia.share.common.model.dto.expert.business.document.BusinessDocumentDto;
import com.simia.share.common.model.dto.expert.tag.TagDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BusinessGroupTagDto {
    
    private TagDto tag;
    
    private List<BusinessDocumentDto> business;
}
