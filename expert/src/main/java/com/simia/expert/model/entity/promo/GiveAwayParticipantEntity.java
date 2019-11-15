package com.simia.expert.model.entity.promo;

import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GiveAwayParticipantEntity extends AuditableDefaultEntity {

    @Column(name = "give_away_id")
    private UUID giveAwayId;

    @Column(name = "is_winner")
    private Boolean isWinner;

    @Column(name = "winner_position")
    private Integer winnerPosition;
}