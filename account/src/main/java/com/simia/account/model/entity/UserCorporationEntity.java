package com.simia.account.model.entity;

import com.simia.share.common.model.entity.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_corporation")
public class UserCorporationEntity extends DefaultEntity {

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "corporation_id")
    private UUID corporationId;

}
