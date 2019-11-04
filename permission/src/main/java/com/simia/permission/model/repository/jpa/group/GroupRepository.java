package com.simia.permission.model.repository.jpa.group;

import com.simia.permission.model.entity.group.GroupEntity;
import com.simia.share.common.model.dto.permission.enumerated.GroupPurpose;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface GroupRepository extends JpaRepository<GroupEntity, UUID> {

    List<GroupEntity> findByPurposeIn(List<GroupPurpose> purposes);

    List<GroupEntity> findByPurposeInAndApplicationId(List<GroupPurpose> purposes, UUID applicationId);
}
