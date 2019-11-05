package com.simia.expert.model.repository.jpa.popular;

import com.simia.expert.model.entity.popular.BusinessPopularEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BusinessPopularRepository extends JpaRepository<BusinessPopularEntity, UUID> {
    
    BusinessPopularEntity findByBusinessId(UUID businessId);
    
    List<BusinessPopularEntity> findByBusinessIdInOrderByCountDesc(Iterable<UUID> businessIds);
    
    List<BusinessPopularEntity> findByBusinessIdIn(Iterable<UUID> businessIds, Pageable pageable);
}
