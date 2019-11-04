package com.simia.account.service.user;

import com.simia.account.model.entity.UserCorporationEntity;
import com.simia.share.common.model.dto.account.user.UserCorporationDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 */
public interface UserCorporationService extends DefaultService<UserCorporationDto, UserCorporationEntity> {

    List<UserCorporationDto> getAllByUserIdAndCorporationId(UUID userId, List<UUID> corporationIds);
}
