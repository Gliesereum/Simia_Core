package com.simia.language.service.impl;

import com.simia.language.model.entity.PhraseEntity;
import com.simia.language.model.repository.jpa.PhraseRepository;
import com.simia.language.service.PhraseService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.language.PhraseDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Slf4j
@Service
public class PhraseServiceImpl extends DefaultServiceImpl<PhraseDto, PhraseEntity> implements PhraseService {

    private static final Class<PhraseDto> DTO_CLASS = PhraseDto.class;
    private static final Class<PhraseEntity> ENTITY_CLASS = PhraseEntity.class;

    private final PhraseRepository phraseRepository;

    @Autowired
    public PhraseServiceImpl(PhraseRepository phraseRepository, DefaultConverter defaultConverter) {
        super(phraseRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.phraseRepository = phraseRepository;
    }

    @Override
    @Transactional
    public void deleteByPackageId(UUID packageId) {
        if (packageId != null) {
            phraseRepository.deleteAllByPackageId(packageId);
        }
    }
}
