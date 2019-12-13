package com.simia.share.common.model.dto.expert.business.popular;

import com.simia.share.common.model.dto.DefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusinessPopularDto extends DefaultDto {
    
    private UUID businessId;
    
    private Long count;
}