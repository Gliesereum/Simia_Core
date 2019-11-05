package com.simia.expert.service.car.impl;

import com.simia.expert.model.entity.car.YearCarEntity;
import com.simia.expert.model.repository.jpa.car.YearCarRepository;
import com.simia.expert.service.car.YearCarService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.car.YearCarDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author vitalij
 * @version 1.0
 */
@Slf4j
@Service
public class YearCarServiceImpl extends DefaultServiceImpl<YearCarDto, YearCarEntity> implements YearCarService {

    private static final Class<YearCarDto> DTO_CLASS = YearCarDto.class;
    private static final Class<YearCarEntity> ENTITY_CLASS = YearCarEntity.class;

    public YearCarServiceImpl(YearCarRepository repository, DefaultConverter defaultConverter) {
        super(repository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
    }
}
