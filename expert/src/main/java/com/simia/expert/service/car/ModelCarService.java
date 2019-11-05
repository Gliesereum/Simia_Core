package com.simia.expert.service.car;

import com.simia.expert.model.entity.car.ModelCarEntity;
import com.simia.share.common.model.dto.expert.car.ModelCarDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface ModelCarService extends DefaultService<ModelCarDto, ModelCarEntity> {

    List<ModelCarDto> getAllByBrandId(UUID id);
}
