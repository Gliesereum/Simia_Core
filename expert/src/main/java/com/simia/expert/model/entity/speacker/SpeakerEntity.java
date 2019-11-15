package com.simia.expert.model.entity.speacker;

import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpeakerEntity extends AuditableDefaultEntity {

    @Column(name = "promotion_id")
    private UUID promotionId;

    @Column(name = "expert_position")
    private String expertPosition;

    @Column(name = "percent")
    private Integer percent;

}