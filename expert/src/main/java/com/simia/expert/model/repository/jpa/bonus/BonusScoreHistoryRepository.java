package com.simia.expert.model.repository.jpa.bonus;

import com.simia.expert.model.entity.bonus.BonusScoreHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface BonusScoreHistoryRepository extends JpaRepository<BonusScoreHistoryEntity, UUID> {
}
