package com.simia.share.common.model.dto.expert.service;

import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.enumerated.ObjectState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LitePackageDto extends DefaultDto {

    private String name;

    private Integer discount;

    private Integer duration;

    private UUID businessId;

    private ObjectState objectState;

}
