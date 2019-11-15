package com.simia.expert.model.entity.content;

import com.simia.expert.model.entity.category.CategoryEntity;
import com.simia.expert.model.entity.comment.CommentEntity;
import com.simia.expert.model.entity.expert.ExpertEntity;
import com.simia.expert.model.entity.promo.PromotionEntity;
import com.simia.expert.model.entity.tag.TagEntity;
import com.simia.share.common.model.dto.expert.enumerated.ContentType;
import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PackageEntity extends AuditableDefaultEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private ExpertEntity expert;

    @Column(name = "icon_url")
    private String iconUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", insertable = false, updatable = false)
    private PromotionEntity promotion;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "package_content",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id"))
    private Set<ContentEntity> contents = new HashSet<>();
}