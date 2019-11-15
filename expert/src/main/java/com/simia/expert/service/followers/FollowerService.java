package com.simia.expert.service.followers;

import com.simia.expert.model.entity.followers.FollowerEntity;
import com.simia.share.common.model.dto.expert.followers.FollowerDto;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.List;
import java.util.UUID;

public interface FollowerService extends AuditableService<FollowerDto, FollowerEntity> {

    List<FollowerDto> getAllByExpertId(UUID id);

    List<FollowerDto> followToExpert(UUID id);

    List<FollowerDto> unfollowFromExpert(UUID id);

    List<FollowerDto> iFollowedToExperts();
}