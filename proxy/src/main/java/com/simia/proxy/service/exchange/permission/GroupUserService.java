package com.simia.proxy.service.exchange.permission;

import com.simia.share.common.model.dto.permission.group.GroupDto;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface GroupUserService {

    List<GroupDto> getUserGroups(UUID userId, UUID applicationId, String jwt);
}
