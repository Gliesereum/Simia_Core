package com.simia.share.common.model.dto.expert.record;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class RecordUsageCountDto {
    
    private UUID businessId;
    
    private UUID objectId;
    
    private Long count;
}
