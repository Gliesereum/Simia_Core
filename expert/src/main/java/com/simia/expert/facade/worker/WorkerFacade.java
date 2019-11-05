package com.simia.expert.facade.worker;

import com.simia.share.common.model.dto.expert.business.WorkerDto;

/**
 * @author vitalij
 * @version 1.0
 */
public interface WorkerFacade {

    void sendMessageToWorkerAfterCreate(WorkerDto worker);
}
