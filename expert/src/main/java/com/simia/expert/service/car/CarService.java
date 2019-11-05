package com.simia.expert.service.car;

import com.simia.expert.model.entity.car.CarEntity;
import com.simia.share.common.model.dto.expert.car.CarDto;
import com.simia.share.common.model.dto.expert.car.CarInfoDto;
import com.simia.share.common.model.dto.expert.car.LiteCarDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface CarService extends DefaultService<CarDto, CarEntity> {

    List<CarDto> getAllByUserId(UUID userId);

    void deleteByUserId(UUID id);

    void checkCarExistInCurrentUser(UUID id);

    CarDto checkAndGetCarExistInCurrentUser(UUID id);

    boolean carExistByIdAndUserId(UUID id, UUID userId);

    void addService(UUID idCar, UUID idService);

    void removeService(UUID idCar, UUID idService);

    CarInfoDto getCarInfo(UUID idCar);

    void addFilterAttribute(UUID idCar, UUID idAttribute);

    void removeFilterAttribute(UUID idCar, UUID idAttribute);

    CarDto setFavorite(UUID idCar);

    List<LiteCarDto> getCarByBrandIds(List<UUID> brandsIds);

    List<CarDto> getAsWorker(UUID clientId, UUID corporationId);

    UUID getFavoriteCarByUserId(UUID userId);
}
