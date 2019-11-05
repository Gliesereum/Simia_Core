package com.simia.expert.model.entity.filter;

import com.simia.expert.model.entity.filter.descriptions.FilterAttributeDescriptionEntity;
import com.simia.share.common.model.entity.DefaultEntity;
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
@Table(name = "filter_attribute")
public class FilterAttributeEntity extends DefaultEntity {

    @Column(name = "value")
    private String value;

    @Column(name = "title")
    private String title;

    @Column(name = "filter_id")
    private UUID filterId;

    @OneToMany
    @JoinColumn(name = "object_id", insertable = false, updatable = false)
    private Set<FilterAttributeDescriptionEntity> descriptions = new HashSet<>();

}
