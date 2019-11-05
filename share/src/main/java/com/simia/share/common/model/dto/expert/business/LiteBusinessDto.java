package com.simia.share.common.model.dto.expert.business;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LiteBusinessDto extends AuditableDefaultDto {

    private UUID corporationId;

    private String name;

    private String description;

    private String logoUrl;
    
    private String coverUrl;
    
    private String siteUrl;
    
    private String videoUrl;

    private String address;

    private String phone;

    private String addPhone;

    private Double latitude;

    private Double longitude;

    private Integer timeZone;

    private UUID businessCategoryId;

    private boolean cashlessPayment;

    private Boolean businessVerify;

}
