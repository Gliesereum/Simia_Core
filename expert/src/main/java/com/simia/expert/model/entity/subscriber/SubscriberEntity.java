package com.simia.expert.model.entity.subscriber;

import com.simia.share.common.model.dto.expert.enumerated.ContentType;
import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "subscriber")
public class SubscriberEntity extends AuditableDefaultEntity {

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "content_type")
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column(name = "content_id")
    private UUID contentId;

}