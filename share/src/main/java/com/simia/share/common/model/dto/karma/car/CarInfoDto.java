package com.simia.share.common.model.dto.karma.car;

import com.simia.share.common.model.dto.karma.filter.FilterAttributeDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class CarInfoDto {

    private List<String> serviceClassIds;

    private List<FilterAttributeDto> filterAttributes;
}
