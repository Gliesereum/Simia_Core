package com.simia.expert.facade.notification;

import com.simia.share.common.model.dto.expert.business.AbstractBusinessDto;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface BusinessNotificationFacade {

    void newBusinessNotification(AbstractBusinessDto business);
}
