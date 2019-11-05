package com.simia.expert.service.car.impl;

import com.simia.expert.model.entity.car.CarServiceClassEntity;
import com.simia.expert.model.repository.jpa.car.CarServiceClassRepository;
import com.simia.expert.service.car.CarServiceClassService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.car.CarServiceClassDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class CarServiceClassServiceImpl extends DefaultServiceImpl<CarServiceClassDto, CarServiceClassEntity> implements CarServiceClassService {

    private static final Class<CarServiceClassDto> DTO_CLASS = CarServiceClassDto.class;
    private static final Class<CarServiceClassEntity> ENTITY_CLASS = CarServiceClassEntity.class;

    private final CarServiceClassRepository carServiceClassRepository;

    @Autowired
    public CarServiceClassServiceImpl(CarServiceClassRepository carServiceClassRepository, DefaultConverter defaultConverter) {
        super(carServiceClassRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.carServiceClassRepository = carServiceClassRepository;
    }

    @Override
    @Transactional
    public void deleteByIdCarAndIdService(UUID idCar, UUID idService) {
        carServiceClassRepository.deleteByCarIdAndAndServiceClassId(idCar, idService);
    }
}
