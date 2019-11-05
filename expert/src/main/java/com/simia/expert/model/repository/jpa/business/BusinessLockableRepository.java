package com.simia.expert.model.repository.jpa.business;

import com.simia.expert.model.entity.business.BaseBusinessEntity;
import com.simia.share.common.model.enumerated.ObjectState;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface BusinessLockableRepository {

    BaseBusinessEntity findByIdAndObjectStateAndLock(UUID id, ObjectState objectState);
}
