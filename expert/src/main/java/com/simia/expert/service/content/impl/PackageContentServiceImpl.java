package com.simia.expert.service.content.impl;

import com.simia.expert.model.entity.content.PackageContentEntity;
import com.simia.expert.model.repository.jpa.content.PackageContentRepository;
import com.simia.expert.service.content.PackageContentService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.content.PackageContentDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class PackageContentServiceImpl extends AuditableServiceImpl<PackageContentDto, PackageContentEntity> implements PackageContentService {

    private static final Class<PackageContentDto> DTO_CLASS = PackageContentDto.class;
    private static final Class<PackageContentEntity> ENTITY_CLASS = PackageContentEntity.class;

    private final PackageContentRepository packageContentRepository;

    @Autowired
    public PackageContentServiceImpl(PackageContentRepository packageContentRepository, DefaultConverter defaultConverter) {
        super(packageContentRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.packageContentRepository = packageContentRepository;
    }

    @Override
    public List<PackageContentDto> getByPackageId(UUID id) {
        List<PackageContentEntity> entities = packageContentRepository.getAllByPackageIdAndObjectState(id, ObjectState.ACTIVE);
        return converter.convert(entities, dtoClass);
    }
}