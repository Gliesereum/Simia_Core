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
@Entity
@Table(name = "city")
public class CityEntity extends DefaultEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "centre_latitude")
    private Double centreLatitude;

    @Column(name = "centre_longitude")
    private Double centreLongitude;
    
}
