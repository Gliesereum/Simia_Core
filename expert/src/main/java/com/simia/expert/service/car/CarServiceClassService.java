package com.simia.expert.service.car;

import com.simia.expert.model.entity.car.CarServiceClassEntity;
import com.simia.share.common.model.dto.expert.car.CarServiceClassDto;
import com.simia.share.common.service.DefaultService;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface CarServiceClassService extends DefaultService<CarServiceClassDto, CarServiceClassEntity> {

    void deleteByIdCarAndIdService(UUID idCar, UUID idService);
}
