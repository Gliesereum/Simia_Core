package com.simia.expert.service.karma.impl;

import com.simia.expert.model.entity.karma.KarmaEntity;
import com.simia.expert.model.repository.jpa.karma.KarmaRepository;
import com.simia.expert.service.karma.KarmaService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.karma.KarmaDto;
import com.simia.share.common.service.DefaultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
public class KarmaServiceImpl extends DefaultServiceImpl<KarmaDto, KarmaEntity> implements KarmaService {

    private static final Class<KarmaDto> DTO_CLASS = KarmaDto.class;
    private static final Class<KarmaEntity> ENTITY_CLASS = KarmaEntity.class;

    private final KarmaRepository karmaRepository;

    @Autowired
    public KarmaServiceImpl(KarmaRepository karmaRepository, DefaultConverter defaultConverter) {
        super(karmaRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.karmaRepository = karmaRepository;
    }

    @Override
    public KarmaDto getByUserId(UUID userId) {
        KarmaDto result = null;
        if (userId != null) {
            KarmaEntity entity = karmaRepository.findByObjectId(userId);
            result = converter.convert(entity, dtoClass);
        }
        return result;
    }
}
