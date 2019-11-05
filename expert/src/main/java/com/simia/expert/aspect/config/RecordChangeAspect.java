package com.simia.expert.aspect.config;

import com.simia.expert.facade.notification.RecordNotificationFacade;
import com.simia.expert.service.administrator.BusinessAdministratorService;
import com.simia.expert.service.business.BaseBusinessService;
import com.simia.share.common.model.dto.expert.business.BaseBusinessDto;
import com.simia.share.common.model.dto.expert.enumerated.StatusRecord;
import com.simia.share.common.model.dto.expert.record.BaseRecordDto;
import com.simia.share.common.model.dto.expert.record.RecordNotificationDto;
import com.simia.share.common.util.SecurityUtil;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Component
@Aspect
public class RecordChangeAspect {

    @Autowired
    private RecordNotificationFacade recordNotificationFacade;
    
    @Autowired
    private BaseBusinessService baseBusinessService;
    
    @Autowired
    private BusinessAdministratorService businessAdministratorService;

    @AfterReturning(pointcut = "@annotation(com.simia.expert.aspect.annotation.RecordUpdate)", returning = "retVal")
    public void recordUpdateEvent(BaseRecordDto retVal) {
        if ((retVal != null) && (retVal.getClientId() != null)) {
            StatusRecord statusRecord = retVal.getStatusRecord();
            if ((statusRecord != null) && statusRecord.equals(StatusRecord.CANCELED) && retVal.getClientId().equals(SecurityUtil.getUserId())) {
                recordNotificationFacade.recordBusinessNotification(retVal);
            } else {
                recordNotificationFacade.recordClientNotification(retVal);
            }
        }

    }

    @AfterReturning(pointcut = "@annotation(com.simia.expert.aspect.annotation.RecordCreate)", returning = "retVal")
    public void recordCreateEvent(BaseRecordDto retVal) {
        if (retVal.getClientId() != null) {
            if (SecurityUtil.isAnonymous() || retVal.getClientId().equals(SecurityUtil.getUserId())) {
                BaseBusinessDto business = baseBusinessService.getById(retVal.getBusinessId());
                List<UUID> userIds = businessAdministratorService.getUserIdsByBusinessId(retVal.getBusinessId());
    
                RecordNotificationDto recordNotificationDto = new RecordNotificationDto();
                recordNotificationDto.setBusiness(business);
                recordNotificationDto.setRecord(retVal);
                recordNotificationDto.setUserIds(userIds);
    
                recordNotificationFacade.recordCreateBusinessNotification(recordNotificationDto);
            } else {
                recordNotificationFacade.recordClientNotification(retVal);
            }
        }
    }

}
