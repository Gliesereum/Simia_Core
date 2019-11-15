package com.simia.expert.model.entity.promo;

import com.simia.share.common.model.dto.expert.enumerated.GiveAwayState;
import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiveAwayEntity extends AuditableDefaultEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "promotion_id", insertable = false, updatable = false)
    private UUID promotionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private PromotionEntity promotion;

}