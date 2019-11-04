package com.simia.permission.service.application;

import com.simia.permission.model.entity.application.ApplicationEntity;
import com.simia.share.common.model.dto.permission.application.ApplicationDto;
import com.simia.share.common.service.DefaultService;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface ApplicationService extends DefaultService<ApplicationDto, ApplicationEntity> {

    ApplicationDto check(UUID id);

}
