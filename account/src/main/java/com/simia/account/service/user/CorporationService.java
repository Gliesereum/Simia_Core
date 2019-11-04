package com.simia.account.service.user;

import com.simia.account.model.entity.CorporationEntity;
import com.simia.share.common.model.dto.account.user.CorporationDto;
import com.simia.share.common.model.dto.account.user.CorporationSharedOwnershipDto;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author vitalij
 */
public interface CorporationService extends AuditableService<CorporationDto, CorporationEntity> {

    void addOwnerCorporation(CorporationSharedOwnershipDto dto);

    void removeOwnerCorporation(UUID id);

    void checkCurrentUserForPermissionActionThisCorporation(UUID id);

    void setKycApproved(UUID objectId);

    List<CorporationDto> getByUserId(UUID userId);

    Map<UUID, List<CorporationDto>> getCorporationByUserIds(List<UUID> userIds);
}
