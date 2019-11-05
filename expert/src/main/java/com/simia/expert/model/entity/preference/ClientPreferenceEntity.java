package com.simia.expert.model.entity.preference;

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
@Table(name = "client_preference")
public class ClientPreferenceEntity extends DefaultEntity {

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "service_id")
    private UUID serviceId;

    @Column(name = "business_category_id")
    private UUID businessCategoryId;
}
