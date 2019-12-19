package com.simia.share.common.model.dto.expert.followers;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FollowerDto extends AuditableDefaultDto {

    private UUID userId;

    private UUID expertId;
}
