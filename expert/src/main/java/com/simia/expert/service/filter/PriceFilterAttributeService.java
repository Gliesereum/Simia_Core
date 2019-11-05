package com.simia.expert.service.filter;

import com.simia.expert.model.entity.filter.PriceFilterAttributeEntity;
import com.simia.share.common.model.dto.expert.filter.PriceFilterAttributeDto;
import com.simia.share.common.service.DefaultService;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface PriceFilterAttributeService extends DefaultService<PriceFilterAttributeDto, PriceFilterAttributeEntity> {

    void deleteByPriceIdAndFilterId(UUID idPrice, UUID filterAttributeId);

    void deleteByPriceId(UUID idPrice);

    boolean existByPriceIdAndAttributeId(UUID idPrice, UUID filterAttributeId);

}
