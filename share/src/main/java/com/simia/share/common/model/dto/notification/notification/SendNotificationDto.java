package com.simia.share.common.model.dto.notification.notification;

import com.simia.share.common.model.dto.notification.enumerated.SubscribeDestination;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class SendNotificationDto {

    @NotNull
    private SubscribeDestination destination;
    
    private UUID objectId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String body;

    private List<UUID> userIds;
    
    private Map<String, String> data;

    @NotNull
    private UUID businessId;
}
