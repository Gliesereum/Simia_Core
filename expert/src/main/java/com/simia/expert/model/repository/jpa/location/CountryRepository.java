package com.simia.expert.model.repository.jpa.location;

import com.simia.expert.model.entity.location.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {
}
