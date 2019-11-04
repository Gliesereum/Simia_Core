package com.simia.payment.model.repository.jpa.wayforpay;

import com.simia.payment.model.entity.wayforpay.WfpCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface WfpCardRepository extends JpaRepository<WfpCardEntity, UUID> {

    List<WfpCardEntity> getByOwnerId(UUID ownerId);

    WfpCardEntity getById(UUID id);

}
