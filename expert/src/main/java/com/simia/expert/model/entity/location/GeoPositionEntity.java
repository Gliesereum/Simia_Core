package com.simia.expert.model.entity.location;

import com.simia.share.common.model.entity.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GeoPositionEntity extends DefaultEntity {


    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "object_id")
    private UUID objectId;
}
