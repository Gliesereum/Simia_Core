package com.simia.share.common.model.dto.expert.promo;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiveAwayParticipantDto extends AuditableDefaultDto {

    private UUID giveAwayId;

    private UUID userId;

    private Boolean isWinner;

    private Integer winnerPosition;
}
