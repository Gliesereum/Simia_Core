package com.simia.expert.service.karma;

import com.simia.expert.model.entity.karma.KarmaEntity;
import com.simia.share.common.model.dto.expert.karma.KarmaDto;
import com.simia.share.common.service.DefaultService;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface KarmaService extends DefaultService<KarmaDto, KarmaEntity> {

    KarmaDto getByUserId(UUID userId);
}
