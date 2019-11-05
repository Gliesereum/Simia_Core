package com.simia.expert.model.entity.service;

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
@Table(name = "service_class_price")
public class ServiceClassPriceEntity extends DefaultEntity {

    @Column(name = "price_id")
    private UUID priceId;

    @Column(name = "service_class_id")
    private UUID serviceClassId;

}
