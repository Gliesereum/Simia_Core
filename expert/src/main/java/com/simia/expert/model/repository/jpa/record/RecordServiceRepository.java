package com.simia.expert.model.repository.jpa.record;

import com.simia.expert.model.entity.record.RecordServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 * @since 12/7/18
 */
public interface RecordServiceRepository extends JpaRepository<RecordServiceEntity, UUID> {

    List<RecordServiceEntity> findAllByRecordIdIn(List<UUID> recordId);
}
