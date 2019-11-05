package com.simia.expert.service.service;

import com.simia.expert.model.entity.service.PackageServiceEntity;
import com.simia.share.common.model.dto.expert.service.PackageServiceDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface PackageServiceService extends DefaultService<PackageServiceDto, PackageServiceEntity> {

    void deleteByPackageIdAndServicePriceIDs(UUID packageId, List<UUID> servicePricesIds);

    void deleteByPackageId(UUID packageId);
}
