package com.simia.expert.service.preference;

import com.simia.expert.model.entity.preference.ClientPreferenceEntity;
import com.simia.share.common.model.dto.expert.preference.ClientPreferenceDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface ClientPreferenceService extends DefaultService<ClientPreferenceDto, ClientPreferenceEntity> {

    List<ClientPreferenceDto> addListByServiceIds(List<UUID> serviceIds);

    ClientPreferenceDto addPreferenceByServiceId(UUID id);

    List<ClientPreferenceDto> getAllByUser();

    List<ClientPreferenceDto> getAllByUserId(UUID id);

    List<ClientPreferenceDto> getAllByUserIdAndBusinessCategoryIds(UUID userId, List<UUID> businessCategoryId);

    void deleteByServiceId(UUID id);

    void deleteAllByUser();
}
