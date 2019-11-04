package com.gliesereum.share.common.exchange.properties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class PermissionProperty {

    private String endpointUpdatePack;

    private String addUserByGroupPurpose;

    private String getGroupUserByGroupPurpose;
}
