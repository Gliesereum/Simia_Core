package com.gliesereum.share.common.model.dto.karma.business.descriptions;

import com.gliesereum.share.common.model.dto.base.description.BaseDescriptionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusinessDescriptionDto extends BaseDescriptionDto {

    private String name;

    private String description;

    private String address;
}
