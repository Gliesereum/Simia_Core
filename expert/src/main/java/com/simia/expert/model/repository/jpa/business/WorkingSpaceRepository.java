package com.simia.expert.model.repository.jpa.business;

import com.simia.expert.model.entity.business.WorkingSpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface WorkingSpaceRepository extends JpaRepository<WorkingSpaceEntity, UUID> {

    List<WorkingSpaceEntity> findByBusinessId(UUID businessId);

    List<WorkingSpaceEntity> findByBusinessIdOrderByIndexNumberAsc(UUID businessId);

    boolean existsByIdAndBusinessId(UUID id, UUID businessId);
}
