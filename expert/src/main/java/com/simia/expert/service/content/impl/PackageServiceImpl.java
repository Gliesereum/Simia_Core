package com.simia.expert.service.content.impl;

import com.simia.expert.model.entity.content.PackageEntity;
import com.simia.expert.model.repository.jpa.content.PackageRepository;
import com.simia.expert.service.content.PackageService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.content.PackageDto;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class PackageServiceImpl extends AuditableServiceImpl<PackageDto, PackageEntity> implements PackageService {

    private static final Class<PackageDto> DTO_CLASS = PackageDto.class;
    private static final Class<PackageEntity> ENTITY_CLASS = PackageEntity.class;

    private final PackageRepository packageRepository;

    @Autowired
    public PackageServiceImpl(PackageRepository packageRepository, DefaultConverter defaultConverter) {
        super(packageRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.packageRepository = packageRepository;
    }
}