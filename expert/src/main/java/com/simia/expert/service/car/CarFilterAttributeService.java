package com.simia.expert.service.car;

import com.simia.expert.model.entity.car.CarFilterAttributeEntity;
import com.simia.share.common.model.dto.expert.car.CarFilterAttributeDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface CarFilterAttributeService extends DefaultService<CarFilterAttributeDto, CarFilterAttributeEntity> {

    void deleteByCarIdAndFilterAttributeId(UUID idCar, UUID filterAttributeId);

    void deleteByCarIdAndFilterAttributeIds(UUID idCar, List<UUID> filterAttributeIds);

    boolean existsByCarIdAndFilterAttributeId(UUID idCar, UUID filterAttributeId);

}
