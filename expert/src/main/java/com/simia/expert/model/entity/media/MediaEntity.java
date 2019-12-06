package com.simia.expert.model.entity.media;

import com.simia.share.common.model.dto.expert.enumerated.MediaType;
import com.simia.share.common.model.entity.AuditableDefaultEntity;
import com.simia.share.common.model.entity.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "media")
public class MediaEntity extends AuditableDefaultEntity {

    @Column(name = "object_id")
    private UUID objectId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;
    
    @Column(name = "position")
    private Integer position;

    @Column(name = "media_type")
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;
}
