package com.simia.share.common.model.dto.permission.endpoint;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class EndpointPackDto {

    private List<EndpointDto> endpoints;

    private String moduleUrl;

    private String moduleName;
}
