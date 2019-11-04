package com.simia.share.common.exchange.service.permission;

import com.simia.share.common.model.dto.permission.enumerated.GroupPurpose;
import com.simia.share.common.model.dto.permission.group.GroupUserDto;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface GroupUserExchangeService {

    List<GroupUserDto> addUserByGroupPurpose(UUID userId, GroupPurpose groupPurpose);

    List<UUID> getUserIdsByGroupPurpose(GroupPurpose groupPurpose);
}
