package com.simia.share.common.model.dto.notification.notification;

import com.simia.share.common.model.dto.notification.enumerated.SubscribeDestination;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class NotificationDto<T> {

    private SubscribeDestination subscribeDestination;

    private UUID objectId;

    private T data;
    
    private List<UUID> userIds;
}
