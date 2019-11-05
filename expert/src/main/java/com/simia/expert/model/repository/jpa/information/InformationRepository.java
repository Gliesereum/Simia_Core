package com.simia.expert.model.repository.jpa.information;

import com.simia.expert.model.entity.information.InformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface InformationRepository extends JpaRepository<InformationEntity, UUID> {

    List<InformationEntity> findByTagOrderByIndex(String tag);

    List<InformationEntity> findByTagAndIsoCodeOrderByIndex(String tag, String isoCode);

    List<InformationEntity> findByOrderByIndex();
}
