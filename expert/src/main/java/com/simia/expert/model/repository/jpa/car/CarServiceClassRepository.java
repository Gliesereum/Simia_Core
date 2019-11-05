package com.simia.expert.model.repository.jpa.car;

import com.simia.expert.model.entity.car.CarServiceClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface CarServiceClassRepository extends JpaRepository<CarServiceClassEntity, UUID> {

    void deleteByCarIdAndAndServiceClassId(UUID idCar, UUID idService);
}
