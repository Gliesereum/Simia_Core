package com.simia.expert.facade.group;

import com.simia.share.common.model.dto.permission.enumerated.GroupPurpose;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface GroupUserExchangeFacade {

    void addUserByGroupPurposeAsync(UUID userId, GroupPurpose groupPurpose);
}
