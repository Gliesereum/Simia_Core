package com.simia.expert.service.service;

import com.simia.expert.model.entity.service.ServicePriceEntity;
import com.simia.share.common.model.dto.expert.service.LiteServicePriceDto;
import com.simia.share.common.model.dto.expert.service.ServicePriceDto;
import com.simia.share.common.model.dto.expert.tag.TagDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface ServicePriceService extends DefaultService<ServicePriceDto, ServicePriceEntity> {

    List<ServicePriceDto> getAllByPackage(UUID id);

    List<ServicePriceDto> getByBusinessId(UUID id);

    List<ServicePriceDto> getByBusinessIds(List<UUID> ids);

    Map<UUID, List<ServicePriceDto>> getMapByBusinessIds(List<UUID> ids);

    void addFilterAttribute(UUID idPrice, UUID idAttribute);

    ServicePriceDto addFilterAttributes(UUID idPrice, List<UUID> idsAttribute);

    void removeFilterAttribute(UUID idPrice, UUID idAttribute);

    ServicePriceDto getByIdAndRefresh(UUID id);

    List<LiteServicePriceDto> getLiteServicePriceByBusinessId(UUID id);

    int getCountByBusinessIdAndServicePriceIds(UUID businessId, List<UUID> servicePriceIds);
    
    Map<UUID, LiteServicePriceDto> getMapByIds(List<UUID> servicePriceIds);
    
    List<LiteServicePriceDto> getLiteByIds(List<UUID> ids);

    List<TagDto> addTag(UUID tagId, UUID idPrice);

    List<TagDto> getTags(UUID idPrice);

    List<TagDto> saveTags(List<UUID> tagId, UUID idPrice);

    List<TagDto> removeTag(UUID tagId, UUID idPrice);
}
