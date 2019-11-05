package com.simia.expert.model.repository.jpa.location;

import com.simia.expert.model.entity.location.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityRepository extends JpaRepository<CityEntity, UUID> {
}
