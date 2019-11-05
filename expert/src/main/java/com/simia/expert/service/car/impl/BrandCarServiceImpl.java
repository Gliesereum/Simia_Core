package com.simia.expert.service.car.impl;

import com.simia.expert.model.entity.car.BrandCarEntity;
import com.simia.expert.model.repository.jpa.car.BrandCarRepository;
import com.simia.expert.service.car.BrandCarService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.car.BrandCarDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class BrandCarServiceImpl extends DefaultServiceImpl<BrandCarDto, BrandCarEntity> implements BrandCarService {

    private static final Class<BrandCarDto> DTO_CLASS = BrandCarDto.class;
    private static final Class<BrandCarEntity> ENTITY_CLASS = BrandCarEntity.class;

    private BrandCarRepository brandCarRepository;

    public BrandCarServiceImpl(BrandCarRepository repository, DefaultConverter defaultConverter) {
        super(repository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.brandCarRepository = repository;
    }

    @Override
    public BrandCarDto getByName(String name) {
        BrandCarDto result = null;
        if (StringUtils.isNotEmpty(name)) {
            BrandCarEntity entity = brandCarRepository.findByName(name);
            result = converter.convert(entity, dtoClass);
        }
        return result;
    }

    @Override
    public BrandCarDto getByNameOrCreate(String name) {
        BrandCarDto result = null;
        if (StringUtils.isNotEmpty(name)) {
            result = getByName(name);
            if (result == null) {
                BrandCarDto newBrand = new BrandCarDto();
                newBrand.setName(name);
                result = super.create(newBrand);
            }
        }
        return result;
    }

    @Override
    public List<BrandCarDto> getAll() {
        List<BrandCarDto> result = super.getAll();
        result.sort(Comparator.comparing(BrandCarDto::getName));
        return result;
    }
}
