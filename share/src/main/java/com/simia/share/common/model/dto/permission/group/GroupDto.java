package com.simia.share.common.model.dto.permission.group;

import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.permission.endpoint.EndpointDto;
import com.simia.share.common.model.dto.permission.enumerated.GroupPurpose;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GroupDto extends DefaultDto {

    private String title;

    private String description;

    private GroupPurpose purpose;

    private boolean isActive;

    private String inactiveMessage;

    private UUID parentGroupId;

    private UUID applicationId;

    private GroupDto parentGroup;

    private List<EndpointDto> endpoints = new ArrayList<>();

    private List<GroupUserDto> users = new ArrayList<>();
}
