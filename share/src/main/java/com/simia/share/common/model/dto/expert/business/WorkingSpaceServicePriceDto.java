package com.simia.share.common.model.dto.expert.business;

import com.simia.share.common.model.dto.DefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WorkingSpaceServicePriceDto extends DefaultDto {

    @NonNull
    private UUID priceId;

    @NonNull
    private UUID workingSpaceId;
}
