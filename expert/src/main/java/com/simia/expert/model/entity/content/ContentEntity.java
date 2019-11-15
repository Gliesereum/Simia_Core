package com.simia.expert.model.entity.content;

import com.simia.expert.model.entity.category.CategoryEntity;
import com.simia.expert.model.entity.comment.CommentEntity;
import com.simia.expert.model.entity.expert.ExpertEntity;
import com.simia.expert.model.entity.media.MediaEntity;
import com.simia.expert.model.entity.promo.GiveAwayEntity;
import com.simia.expert.model.entity.promo.PromotionEntity;
import com.simia.expert.model.entity.speacker.SpeakerEntity;
import com.simia.expert.model.entity.tag.TagEntity;
import com.simia.share.common.model.dto.expert.enumerated.ContentType;
import com.simia.share.common.model.dto.expert.enumerated.PromoType;
import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContentEntity extends AuditableDefaultEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "category_id", insertable = false, updatable = false)
    private UUID categoryId;

    @Column(name = "promotion_id", insertable = false, updatable = false)
    private UUID promotionId;

    @Column(name = "object_id", insertable = false, updatable = false)
    private UUID mediaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private ExpertEntity expert;

    @Column(name = "icon_url")
    private String iconUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column(name = "content_type")
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "is_starting")
    private Boolean isStarting;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "object_tag",
            joinColumns = @JoinColumn(name = "object_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagEntity> tags = new HashSet<>();

}