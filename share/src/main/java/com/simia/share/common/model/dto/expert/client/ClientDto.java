package com.simia.share.common.model.dto.expert.client;

import com.simia.share.common.model.dto.AuditableDefaultDto;
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
@EqualsAndHashCode(callSuper = false)
public class ClientDto extends AuditableDefaultDto {

    private UUID userId;

    private String firstName;

    private String lastName;

    private String middleName;

    private String phone;

    private String email;

    private String avatarUrl;

    private List<UUID> businessIds = new ArrayList<>();

    private List<UUID> corporationIds = new ArrayList<>();
}
