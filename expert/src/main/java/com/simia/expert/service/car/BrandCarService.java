package com.simia.expert.service.car;

import com.simia.expert.model.entity.car.BrandCarEntity;
import com.simia.share.common.model.dto.expert.car.BrandCarDto;
import com.simia.share.common.service.DefaultService;

/**
 * @author vitalij
 * @version 1.0
 */
public interface BrandCarService extends DefaultService<BrandCarDto, BrandCarEntity> {

    BrandCarDto getByName(String name);

    BrandCarDto getByNameOrCreate(String name);
}
