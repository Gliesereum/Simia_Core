package com.simia.expert.facade.notification.impl;

import com.simia.expert.facade.notification.RecordNotificationFacade;
import com.simia.expert.facade.notification.RecordRemindFacade;
import com.simia.expert.service.record.BaseRecordService;
import com.simia.share.common.model.dto.expert.enumerated.StatusRecord;
import com.simia.share.common.model.dto.expert.record.BaseRecordDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
public class RecordRemindFacadeImpl implements RecordRemindFacade {

    @Autowired
    private BaseRecordService baseRecordService;

    @Autowired
    private RecordNotificationFacade recordNotificationFacade;

    @Scheduled(fixedRate = 9 * 60 * 1000)
    public void recordRemind() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        List<BaseRecordDto> records = baseRecordService.getByTimeBetween(now, 25, 35, StatusRecord.CREATED, false);
        if (CollectionUtils.isNotEmpty(records)) {
            records.forEach(record -> {
                recordNotificationFacade.recordRemindNotification(record);
                baseRecordService.setNotificationSend(record.getId());
            });

        }
    }
}
