package com.simia.share.common.model.dto.expert.business;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class EmptyBusinessDto {

    @NotEmpty
    private String name;

    @NotEmpty
    @Size(min = 5, max = 13)
    private String phone;

    @NotNull
    @Max(90)
    @Min(-90)
    private Double latitude;

    @NotNull
    @Max(180)
    @Min(-180)
    private Double longitude;
    
    private String logoUrl;
    
    private String coverUrl;
    
    private String siteUrl;
    
    private String videoUrl;

    @NotNull
    private UUID businessCategoryId;
}
