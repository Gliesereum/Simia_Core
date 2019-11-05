package com.simia.expert.model.entity.chat;

import com.simia.share.common.model.entity.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "chat")
public class ChatEntity extends DefaultEntity {

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "business_id")
    private UUID businessId;
}