package com.simia.expert.model.entity.followers;

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
public class FollowerEntity extends AuditableDefaultEntity {

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "expert_id")
    private UUID expertId;
}