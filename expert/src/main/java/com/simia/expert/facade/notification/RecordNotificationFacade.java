package com.simia.expert.facade.notification;

import com.simia.share.common.model.dto.expert.record.BaseRecordDto;
import com.simia.share.common.model.dto.expert.record.RecordNotificationDto;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface RecordNotificationFacade {

    void recordBusinessNotification(BaseRecordDto record);
    
    void recordCreateBusinessNotification(RecordNotificationDto recordNotificationDto);

    void recordClientNotification(BaseRecordDto record);

    void recordRemindNotification(BaseRecordDto record);
}
