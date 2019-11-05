package com.simia.expert.model.repository.jpa.preference;

import com.simia.expert.model.entity.preference.ClientPreferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface ClientPreferenceRepository extends JpaRepository<ClientPreferenceEntity, UUID> {

    boolean existsByClientIdAndServiceId(UUID clientId, UUID serviceId);

    List<ClientPreferenceEntity> getAllByClientId(UUID clientId);

    List<ClientPreferenceEntity> getAllByClientIdAndBusinessCategoryIdIn(UUID clientId, List<UUID> businessCategoryIds);

    void deleteAllByClientId(UUID clientId);

    void deleteByClientIdAndServiceId(UUID clientId, UUID serviceId);
}
