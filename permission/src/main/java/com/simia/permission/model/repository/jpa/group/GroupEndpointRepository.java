package com.simia.permission.model.repository.jpa.group;

import com.simia.permission.model.entity.group.GroupEndpointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface GroupEndpointRepository extends JpaRepository<GroupEndpointEntity, UUID> {
}
