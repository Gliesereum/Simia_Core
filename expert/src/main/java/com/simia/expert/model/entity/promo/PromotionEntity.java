package com.simia.expert.model.entity.promo;

import com.simia.expert.model.entity.category.CategoryEntity;
import com.simia.expert.model.entity.expert.ExpertEntity;
import com.simia.expert.model.entity.media.MediaEntity;
import com.simia.expert.model.entity.speacker.SpeakerEntity;
import com.simia.expert.model.entity.tag.TagEntity;
import com.simia.share.common.model.dto.expert.enumerated.PromoType;
import com.simia.share.common.model.dto.expert.media.MediaDto;
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
public class PromotionEntity extends AuditableDefaultEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "object_id")
    private UUID objectId;

    @Column(name = "author_id")
    private UUID authorId;

    @Column(name = "price")
    private Integer price;

    @Column(name = "follower_count")
    private Integer followerCount;

    @Column(name = "interested_count")
    private Integer interestedCount;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "finish_date")
    private LocalDateTime finishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToOne(mappedBy = "promotion", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private GiveAwayEntity giveAway;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id", insertable = false, updatable = false)
    private List<MediaEntity> media = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", insertable = false, updatable = false)
    private Set<SpeakerEntity> speakers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "object_tag",
            joinColumns = @JoinColumn(name = "object_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<TagEntity> tags = new HashSet<>();
}