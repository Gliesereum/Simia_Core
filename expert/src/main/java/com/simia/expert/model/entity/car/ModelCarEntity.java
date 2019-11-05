package com.simia.expert.model.entity.car;

import com.simia.share.common.model.entity.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "model_car")
public class ModelCarEntity extends DefaultEntity {

    @Column(name = "brand_id")
    private UUID brandId;

    @Column(name = "name")
    private String name;
}
