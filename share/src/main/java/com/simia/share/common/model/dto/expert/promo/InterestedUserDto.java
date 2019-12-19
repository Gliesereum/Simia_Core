package com.simia.share.common.model.dto.expert.promo;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InterestedUserDto extends AuditableDefaultDto {

    private UUID promotionId;

    private UUID userId;
}
