package com.simia.expert.service.information;

import com.simia.expert.model.entity.information.InformationEntity;
import com.simia.share.common.model.dto.expert.information.InformationDto;
import com.simia.share.common.service.DefaultService;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.List;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface InformationService extends AuditableService<InformationDto, InformationEntity> {

    List<InformationDto> getByTag(String tag);

    List<InformationDto> getByTagAndIsoCode(String tag, String isoCode);
}
