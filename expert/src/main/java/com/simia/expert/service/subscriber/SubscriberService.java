package com.simia.expert.service.subscriber;

import com.simia.expert.model.entity.subscriber.SubscriberEntity;
import com.simia.share.common.model.dto.expert.subscriber.SubscriberDto;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.List;
import java.util.UUID;

public interface SubscriberService extends AuditableService<SubscriberDto, SubscriberEntity> {

    List<SubscriberDto> mySubscriptions();

    SubscriberDto subscribeToContent(UUID contentId);
}