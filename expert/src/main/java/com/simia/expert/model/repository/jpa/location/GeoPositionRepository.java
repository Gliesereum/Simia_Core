package com.simia.expert.model.repository.jpa.location;

import com.simia.expert.model.entity.location.GeoPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GeoPositionRepository extends JpaRepository<GeoPositionEntity, UUID> {
}
