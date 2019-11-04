package com.simia.language.service;

import com.simia.language.model.entity.PhraseEntity;
import com.simia.share.common.model.dto.language.PhraseDto;
import com.simia.share.common.service.DefaultService;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

public interface PhraseService extends DefaultService<PhraseDto, PhraseEntity> {

    void deleteByPackageId(UUID packageId);
}
