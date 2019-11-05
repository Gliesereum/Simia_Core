package com.simia.share.common.model.dto.expert.service;

import com.simia.share.common.model.dto.DefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PackageServiceDto extends DefaultDto {

    private UUID packageId;

    private UUID serviceId;

    public PackageServiceDto(UUID packageId, UUID serviceId) {
        this.packageId = packageId;
        this.serviceId = serviceId;
    }
}
