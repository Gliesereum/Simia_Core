package com.simia.expert.model.repository.jpa.car;

import com.simia.expert.model.entity.car.BrandCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface BrandCarRepository extends JpaRepository<BrandCarEntity, UUID> {

    BrandCarEntity findByName(String name);
}
