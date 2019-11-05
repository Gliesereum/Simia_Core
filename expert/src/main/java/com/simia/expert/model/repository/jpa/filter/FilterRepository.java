package com.simia.expert.model.repository.jpa.filter;

import com.simia.expert.model.entity.filter.FilterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface FilterRepository extends JpaRepository<FilterEntity, UUID> {

    List<FilterEntity> findAllByBusinessCategoryId(UUID businessCategoryId);

    List<FilterEntity> findAllByBusinessCategoryIdIn(List<UUID> businessCategoryIds);
}
