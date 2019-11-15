package com.simia.expert.model.entity.content;

import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PackageContentEntity extends AuditableDefaultEntity {

    @Column(name = "package_id")
    private UUID packageId;

    @Column(name = "content_id")
    private UUID contentId;

}