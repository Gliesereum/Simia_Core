package com.simia.expert.model.entity.service;

import com.simia.expert.model.entity.service.descriptions.PackageDescriptionEntity;
import com.simia.share.common.model.entity.DefaultEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "package")
public class PackageEntity extends DefaultEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "business_id")
    private UUID businessId;

    @Column(name = "object_state")
    @Enumerated(EnumType.STRING)
    private ObjectState objectState;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "package_service",
            joinColumns = {@JoinColumn(name = "package_id", insertable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "service_id", insertable = false, updatable = false)})
    private Set<ServicePriceEntity> services = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "object_id", insertable = false, updatable = false)
    private Set<PackageDescriptionEntity> descriptions = new HashSet<>();
}
