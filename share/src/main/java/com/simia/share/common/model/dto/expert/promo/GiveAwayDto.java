package com.simia.share.common.model.dto.expert.promo;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import com.simia.share.common.model.dto.expert.enumerated.GiveAwayState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiveAwayDto extends AuditableDefaultDto {

    @NonNull
    private String title;

    private String description;

    @NonNull
    private UUID promotionId;

    private String logoUrl;

    private Integer countWinners;

    private GiveAwayState giveAwayState;

}
