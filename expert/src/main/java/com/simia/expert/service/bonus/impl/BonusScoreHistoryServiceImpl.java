package com.simia.expert.service.bonus.impl;

import com.simia.expert.model.entity.bonus.BonusScoreHistoryEntity;
import com.simia.expert.model.repository.jpa.bonus.BonusScoreHistoryRepository;
import com.simia.expert.service.bonus.BonusScoreHistoryService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.bonus.BonusScoreHistoryDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Slf4j
@Service
public class BonusScoreHistoryServiceImpl extends DefaultServiceImpl<BonusScoreHistoryDto, BonusScoreHistoryEntity> implements BonusScoreHistoryService {

    private static final Class<BonusScoreHistoryDto> DTO_CLASS = BonusScoreHistoryDto.class;
    private static final Class<BonusScoreHistoryEntity> ENTITY_CLASS = BonusScoreHistoryEntity.class;

    private final BonusScoreHistoryRepository bonusScoreHistoryRepository;

    @Autowired
    public BonusScoreHistoryServiceImpl(BonusScoreHistoryRepository bonusScoreHistoryRepository, DefaultConverter defaultConverter) {
        super(bonusScoreHistoryRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.bonusScoreHistoryRepository = bonusScoreHistoryRepository;
    }

    @Override
    public BonusScoreHistoryDto addHistory(UUID bonusScoreId, Double value) {
        BonusScoreHistoryDto result = null;
        if (ObjectUtils.allNotNull(bonusScoreId, value)) {
            BonusScoreHistoryEntity entity = new BonusScoreHistoryEntity();
            entity.setBonusScoreId(bonusScoreId);
            entity.setValue(value);
            entity = bonusScoreHistoryRepository.saveAndFlush(entity);
            result = converter.convert(entity, dtoClass);
        }
        return result;
    }
}
