package com.simia.share.common.model.dto.expert.location;

import com.simia.share.common.model.dto.DefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GeoPositionDto  extends DefaultDto {
    
    private Double longitude;
    
    private Double latitude;

    private UUID objectId;
}
