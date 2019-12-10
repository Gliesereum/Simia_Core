package com.simia.expert.model.entity.category;

import com.simia.share.common.model.entity.AuditableDefaultEntity;
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
public class CategoryEntity extends AuditableDefaultEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "parent_id", insertable = false, unique = false)
    private UUID parentId;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    @JoinColumn(name="parent_id")
    private Set<CategoryEntity> childCategories = new HashSet<>();
}