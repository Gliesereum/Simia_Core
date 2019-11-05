package com.simia.expert.service.administrator;

import com.simia.expert.model.entity.administator.BusinessAdministratorEntity;
import com.simia.share.common.model.dto.expert.administrator.BusinessAdministratorDto;
import com.simia.share.common.model.dto.expert.administrator.DetailedBusinessAdministratorDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface BusinessAdministratorService extends DefaultService<BusinessAdministratorDto, BusinessAdministratorEntity> {

    DetailedBusinessAdministratorDto create(UUID userId, UUID businessId);

    void delete(UUID userId, UUID businessId);

    boolean existByUserIdBusinessId(UUID userId, UUID businessId);

    boolean existByUserIdCorporationId(UUID corporationId, UUID userId);

    boolean existByUserId(UUID userId);
    
    List<UUID> getUserIdsByBusinessId(UUID businessId);

    List<DetailedBusinessAdministratorDto> getByBusinessId(UUID businessId);

    List<DetailedBusinessAdministratorDto> getByCorporationId(UUID corporationId);

    List<BusinessAdministratorDto> getByUserId(UUID userId);
}
