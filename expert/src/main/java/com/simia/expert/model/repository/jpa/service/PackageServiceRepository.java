package com.simia.expert.model.repository.jpa.service;

import com.simia.expert.model.entity.service.PackageServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface PackageServiceRepository extends JpaRepository<PackageServiceEntity, UUID> {

    void deleteAllByPackageIdAndServiceIdIn(UUID packageId, List<UUID> servicePricesIds);

    boolean existsByPackageIdAndServiceId(UUID packageId, UUID serviceId);

    void deleteAllByPackageId(UUID packageId);
}
