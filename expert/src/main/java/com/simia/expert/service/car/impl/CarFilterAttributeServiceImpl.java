package com.simia.expert.service.car.impl;

import com.simia.expert.model.entity.car.CarFilterAttributeEntity;
import com.simia.expert.model.repository.jpa.car.CarFilterAttributeRepository;
import com.simia.expert.service.car.CarFilterAttributeService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.expert.car.CarFilterAttributeDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.KarmaExceptionMessage.*;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class CarFilterAttributeServiceImpl extends DefaultServiceImpl<CarFilterAttributeDto, CarFilterAttributeEntity> implements CarFilterAttributeService {

    private static final Class<CarFilterAttributeDto> DTO_CLASS = CarFilterAttributeDto.class;
    private static final Class<CarFilterAttributeEntity> ENTITY_CLASS = CarFilterAttributeEntity.class;

    private final CarFilterAttributeRepository carFilterAttributeRepository;

    @Autowired
    public CarFilterAttributeServiceImpl(CarFilterAttributeRepository carFilterAttributeRepository, DefaultConverter defaultConverter) {
        super(carFilterAttributeRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.carFilterAttributeRepository = carFilterAttributeRepository;
    }

    @Override
    @Transactional
    public void deleteByCarIdAndFilterAttributeId(UUID idCar, UUID filterAttributeId) {
        carFilterAttributeRepository.deleteByCarIdAndFilterAttributeId(idCar, filterAttributeId);
    }

    @Override
    @Transactional
    public void deleteByCarIdAndFilterAttributeIds(UUID idCar, List<UUID> filterAttributeIds) {
        if ((idCar != null) && CollectionUtils.isNotEmpty(filterAttributeIds)) {
            carFilterAttributeRepository.deleteAllByCarIdAndFilterAttributeIdIn(idCar, filterAttributeIds);
        }
    }

    @Override
    public boolean existsByCarIdAndFilterAttributeId(UUID idCar, UUID filterAttributeId) {
        if (idCar == null){
            throw new ClientException(CAR_ID_EMPTY);
        }
        if (filterAttributeId == null){
            throw new ClientException(FILTER_ATTRIBUTE_ID_IS_EMPTY);
        }
        return carFilterAttributeRepository.existsByCarIdAndFilterAttributeId(idCar, filterAttributeId);
    }

}
