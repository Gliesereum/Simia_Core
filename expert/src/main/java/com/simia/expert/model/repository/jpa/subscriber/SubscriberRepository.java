package com.simia.expert.model.repository.jpa.subscriber;

import com.simia.expert.model.entity.subscriber.SubscriberEntity;
import com.simia.share.common.repository.AuditableRepository;

import java.util.List;
import java.util.UUID;

public interface SubscriberRepository extends AuditableRepository<SubscriberEntity> {

    List<SubscriberEntity> getByUserId(UUID userId);
}