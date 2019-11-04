package com.simia.share.common.model.dto.karma.car;

import com.simia.share.common.model.dto.DefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CarServiceClassDto extends DefaultDto {

    private UUID carId;

    private UUID serviceClassId;

    public CarServiceClassDto(UUID carId, UUID serviceClassId) {
        this.carId = carId;
        this.serviceClassId = serviceClassId;
    }
}
