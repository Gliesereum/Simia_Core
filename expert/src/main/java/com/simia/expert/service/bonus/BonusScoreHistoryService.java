package com.simia.expert.service.bonus;

import com.simia.expert.model.entity.bonus.BonusScoreHistoryEntity;
import com.simia.share.common.model.dto.expert.bonus.BonusScoreHistoryDto;
import com.simia.share.common.service.DefaultService;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface BonusScoreHistoryService extends DefaultService<BonusScoreHistoryDto, BonusScoreHistoryEntity> {

    BonusScoreHistoryDto addHistory(UUID bonusScoreId, Double value);
}
