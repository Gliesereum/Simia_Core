package com.simia.account.service.user;

import com.simia.account.model.entity.CorporationEmployeeEntity;
import com.simia.share.common.model.dto.account.user.CorporationEmployeeDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 */
public interface CorporationEmployeeService extends DefaultService<CorporationEmployeeDto, CorporationEmployeeEntity> {

    List<CorporationEmployeeDto> getAllByCorporationId(UUID id);
}
