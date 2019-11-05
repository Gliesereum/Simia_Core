package com.simia.expert.model.entity.tag;

import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tag")
public class TagEntity extends AuditableDefaultEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
