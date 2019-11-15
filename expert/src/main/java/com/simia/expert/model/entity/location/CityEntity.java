package com.simia.expert.model.entity.location;

import com.simia.share.common.model.entity.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CityEntity extends DefaultEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "centre_latitude")
    private Double centreLatitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private CountryEntity country;

    @OneToMany
    @JoinColumn(name = "object_id", insertable = false, updatable = false)
    private Set<GeoPositionEntity> polygonPoints = new HashSet<>();
}
