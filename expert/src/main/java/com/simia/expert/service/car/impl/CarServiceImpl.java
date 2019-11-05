package com.simia.expert.service.car.impl;

import com.simia.expert.facade.business.BusinessPermissionFacade;
import com.simia.expert.model.entity.car.CarEntity;
import com.simia.expert.model.entity.filter.FilterAttributeEntity;
import com.simia.expert.model.entity.service.ServiceClassEntity;
import com.simia.expert.model.repository.jpa.car.CarRepository;
import com.simia.expert.service.car.CarFilterAttributeService;
import com.simia.expert.service.car.CarService;
import com.simia.expert.service.car.CarServiceClassService;
import com.simia.expert.service.filter.FilterAttributeService;
import com.simia.expert.service.service.ServiceClassService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.expert.car.*;
import com.simia.share.common.model.dto.expert.filter.FilterAttributeDto;
import com.simia.share.common.service.DefaultServiceImpl;
import com.simia.share.common.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.simia.share.common.exception.messages.CommonExceptionMessage.ID_NOT_SPECIFIED;
import static com.simia.share.common.exception.messages.CommonExceptionMessage.USER_IS_ANONYMOUS;
import static com.simia.share.common.exception.messages.KarmaExceptionMessage.*;
import static com.simia.share.common.exception.messages.UserExceptionMessage.USER_ID_IS_EMPTY;
import static com.simia.share.common.exception.messages.UserExceptionMessage.USER_NOT_AUTHENTICATION;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class CarServiceImpl extends DefaultServiceImpl<CarDto, CarEntity> implements CarService {

    private static final Class<CarDto> DTO_CLASS = CarDto.class;
    private static final Class<CarEntity> ENTITY_CLASS = CarEntity.class;

    private final CarRepository carRepository;

    @Autowired
    private CarServiceClassService carServiceClassService;

    @Autowired
    private CarFilterAttributeService carFilterAttributeService;

    @Autowired
    private FilterAttributeService filterAttributeService;

    @Autowired
    private ServiceClassService serviceClassService;

    @Autowired
    private BusinessPermissionFacade businessPermissionFacade;

    public CarServiceImpl(CarRepository carRepository, DefaultConverter defaultConverter) {
        super(carRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDto> getAllByUserId(UUID userId) {
        List<CarEntity> entities = carRepository.getAllByUserId(userId);
        return converter.convert(entities, dtoClass);
    }

    @Override
    public void deleteByUserId(UUID id) {
        carRepository.deleteAllByUserId(id);
    }

    @Override
    public CarDto create(CarDto dto) {
        if (dto != null) {
            UUID userId = SecurityUtil.getUserId();
            if (userId == null) {
                throw new ClientException(USER_IS_ANONYMOUS);
            }
            dto.setUserId(userId);
            dto.setFavorite(false);
            CarEntity entity = converter.convert(dto, entityClass);
            entity = repository.saveAndFlush(entity);
            carRepository.refresh(entity);
            dto = converter.convert(entity, dtoClass);
        }
        return dto;
    }

    @Override
    public CarDto update(CarDto dto) {
        if (dto != null) {
            if (dto.getId() == null) {
                throw new ClientException(ID_NOT_SPECIFIED);
            }
            UUID userId = SecurityUtil.getUserId();
            if (userId == null) {
                throw new ClientException(USER_IS_ANONYMOUS);
            }
            CarDto existed = checkAndGetCarExistInCurrentUser(dto.getId());
            if (dto.getFavorite() == null) {
                dto.setFavorite(existed.getFavorite());
            }
            dto.setUserId(userId);
            dto = super.update(dto);
        }
        return dto;
    }



    @Override
    @Transactional
    public void addService(UUID idCar, UUID idService) {
        checkCarExistInCurrentUser(idCar);
        checkServiceExist(idService);
        carServiceClassService.create(new CarServiceClassDto(idCar, idService));
    }

    @Override
    public void removeService(UUID idCar, UUID idService) {
        checkCarExistInCurrentUser(idCar);
        checkServiceExist(idService);
        carServiceClassService.deleteByIdCarAndIdService(idCar, idService);
    }

    @Override
    public void checkCarExistInCurrentUser(UUID id) {
        if (SecurityUtil.isAnonymous()) {
            throw new ClientException(USER_NOT_AUTHENTICATION);
        }
        if (id == null) {
            throw new ClientException(CAR_ID_EMPTY);
        }
        if (!carRepository.existsByIdAndUserId(id, SecurityUtil.getUserId())) {
            throw new ClientException(CAR_NOT_FOUND);
        }
    }

    @Override
    public CarDto checkAndGetCarExistInCurrentUser(UUID id) {
        if (SecurityUtil.isAnonymous()) {
            throw new ClientException(USER_NOT_AUTHENTICATION);
        }
        if (id == null) {
            throw new ClientException(CAR_ID_EMPTY);
        }
        Optional<CarEntity> optional = carRepository.findByIdAndUserId(id, SecurityUtil.getUserId());
        CarEntity entity = optional.orElseThrow(() -> new ClientException(CAR_NOT_FOUND));
        return converter.convert(entity, dtoClass);
    }

    @Override
    @Transactional
    public CarDto getById(UUID id) {
        return super.getById(id);
    }

    @Override
    public boolean carExistByIdAndUserId(UUID id, UUID userId) {
        if (id == null) {
            throw new ClientException(CAR_ID_EMPTY);
        }
        if (userId == null) {
            throw new ClientException(USER_ID_IS_EMPTY);
        }
        return carRepository.existsByIdAndUserId(id, userId);
    }

    @Override
    public CarInfoDto getCarInfo(UUID idCar) {
        CarInfoDto result = null;
        if (idCar != null) {
            Optional<CarEntity> carOptional = carRepository.findById(idCar);
            if(carOptional.isPresent()) {
                CarEntity car = carOptional.get();
                result = new CarInfoDto();
                Set<ServiceClassEntity> services = car.getServices();
                if (CollectionUtils.isNotEmpty(services)) {
                    result.setServiceClassIds(
                            services.stream()
                                    .map(ServiceClassEntity::getId)
                                    .map(UUID::toString)
                                    .collect(Collectors.toList())
                    );

                }
                Set<FilterAttributeEntity> attributes = car.getAttributes();
                Set<FilterAttributeDto> attributeDtos = converter.convert(attributes, FilterAttributeDto.class);
                if (CollectionUtils.isNotEmpty(attributeDtos)) {
                    result.setFilterAttributes(new ArrayList<>(attributeDtos));
                }
            }
        }
        return result;
    }

    @Override
    @Transactional
    public void addFilterAttribute(UUID idCar, UUID idAttribute) {
        checkCarExistInCurrentUser(idCar);
        List<UUID> attributes = filterAttributeService.checkFilterAttributeExistAndGetAllIdsByFilterId(idAttribute);
        carFilterAttributeService.deleteByCarIdAndFilterAttributeIds(idCar, attributes);
        carFilterAttributeService.create(new CarFilterAttributeDto(idCar,idAttribute));
    }

    @Override
    @Transactional
    public void removeFilterAttribute(UUID idCar, UUID idAttribute) {
        checkCarExistInCurrentUser(idCar);
        filterAttributeService.checkFilterAttributeExist(idAttribute);
        carFilterAttributeService.deleteByCarIdAndFilterAttributeId(idCar, idAttribute);
    }

    @Override
    public CarDto setFavorite(UUID idCar) {
        if (idCar == null) {
            throw new ClientException(ID_NOT_SPECIFIED);
        }
        UUID userId = SecurityUtil.getUserId();
        if (userId == null) {
            throw new ClientException(USER_IS_ANONYMOUS);
        }
        List<CarDto> cars = getAllByUserId(SecurityUtil.getUserId());
        if (CollectionUtils.isEmpty(cars)) {
            throw new ClientException(CAR_NOT_FOUND);
        }
        Optional<CarDto> car = cars.stream().filter(i -> i.getId().equals(idCar)).findFirst();
        if (car.isPresent()) {
            car.get().setFavorite(true);
        } else {
            throw new ClientException(CAR_NOT_FOUND);
        }
        CarDto result = super.update(car.get());
        List<CarDto> other = cars.stream().filter(i -> !i.getId().equals(idCar)).peek(i -> i.setFavorite(false)).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(other)) {
            super.update(other);
        }
        return result;
    }

    @Override
    public List<LiteCarDto> getCarByBrandIds(List<UUID> brandsIds) {
        List<CarEntity> entities = carRepository.getAllByBrandIdIn(brandsIds);
        return converter.convert(entities, LiteCarDto.class);
    }

    @Override
    public List<CarDto> getAsWorker(UUID clientId, UUID corporationId) {
        List<CarDto> result = null;
        if (ObjectUtils.allNotNull(clientId, corporationId)) {
            businessPermissionFacade.checkCurrentUserPermissionToClient(corporationId, clientId);
            List<CarEntity> entities = carRepository.getAllByUserId(clientId);
            result = converter.convert(entities, dtoClass);
        }
        return result;
    }

    @Override
    public UUID getFavoriteCarByUserId(UUID userId) {
        UUID result = null;
        if (userId != null) {
            List<UUID> carIds = carRepository.getIdsByUserIdAndFavorite(userId, true);
            if (CollectionUtils.isNotEmpty(carIds)) {
                result = carIds.get(0);
            }
        }
        return result;
    }

    private void checkServiceExist(UUID id) {
        if (!serviceClassService.existsService(id)) {
            throw new ClientException(SERVICE_CLASS_NOT_FOUND);
        }
    }

    private void checkCarFilterAttributeExist(UUID carId, UUID filterAttributeId) {
        if(carFilterAttributeService.existsByCarIdAndFilterAttributeId(carId, filterAttributeId)){
            throw new ClientException(PAR_CAR_ID_AND_FILTER_ATTRIBUTE_ID_EXIST);
        }
    }
}
