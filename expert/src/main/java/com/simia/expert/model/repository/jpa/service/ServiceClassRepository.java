package com.simia.expert.model.repository.jpa.service;

import com.simia.expert.model.entity.service.ServiceClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 * @since 12/5/18
 */
public interface ServiceClassRepository extends JpaRepository<ServiceClassEntity, UUID> {

    boolean existsById(UUID id);

}
