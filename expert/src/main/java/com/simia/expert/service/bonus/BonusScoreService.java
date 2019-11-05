package com.simia.expert.service.bonus;

import com.simia.expert.model.entity.bonus.BonusScoreEntity;
import com.simia.share.common.model.dto.expert.bonus.BonusScoreDto;
import com.simia.share.common.service.DefaultService;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface BonusScoreService extends DefaultService<BonusScoreDto, BonusScoreEntity> {

    BonusScoreDto getOrCreateByUserId(UUID userId);

    BonusScoreDto updateScore(Double updateValue, UUID userId);

    BonusScoreDto create(UUID userId);
}
