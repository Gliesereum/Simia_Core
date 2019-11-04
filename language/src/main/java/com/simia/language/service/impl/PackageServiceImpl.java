package com.simia.language.service.impl;

import com.simia.language.model.entity.PackageEntity;
import com.simia.language.model.repository.jpa.PackageRepository;
import com.simia.language.service.PackageService;
import com.simia.language.service.PhraseService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.language.LitePackageDto;
import com.simia.share.common.model.dto.language.PackageDto;
import com.simia.share.common.model.dto.language.PackageMapDto;
import com.simia.share.common.model.dto.language.PhraseDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.simia.share.common.exception.messages.LanguageExceptionMessage.PACKAGE_BY_ID_FOR_CREATE_FROM_NOT_EXIST;
import static com.simia.share.common.exception.messages.LanguageExceptionMessage.PACKAGE_WITH_MODULE_AND_ISO_KEY_EXIST;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Slf4j
@Service
public class PackageServiceImpl extends DefaultServiceImpl<PackageDto, PackageEntity> implements PackageService {

    private static final Class<PackageDto> DTO_CLASS = PackageDto.class;
    private static final Class<PackageEntity> ENTITY_CLASS = PackageEntity.class;

    private final PackageRepository packageRepository;

    @Autowired
    private PhraseService phraseService;

    @Autowired
    public PackageServiceImpl(PackageRepository packageRepository, DefaultConverter defaultConverter) {
        super(packageRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.packageRepository = packageRepository;
    }

    @Override
    public List<PackageDto> getByModule(String module) {
        List<PackageDto> result = null;
        if (StringUtils.isNotEmpty(module)) {
            List<PackageEntity> entities = packageRepository.findAllByModule(module);
            result = converter.convert(entities, dtoClass);
        }
        return result;
    }

    @Override
    public PackageDto create(PackageDto packageDto, UUID createFrom) {
        PackageDto result = null;
        if (packageDto != null) {
            if (packageRepository.existsByModuleAndIsoKey(packageDto.getModule(), packageDto.getIsoKey())) {
                throw new ClientException(PACKAGE_WITH_MODULE_AND_ISO_KEY_EXIST);
            }
            List<PhraseDto> phrases = null;
            if (createFrom != null) {
                PackageDto existed = this.getById(createFrom);
                if (existed == null) {
                    throw new ClientException(PACKAGE_BY_ID_FOR_CREATE_FROM_NOT_EXIST);
                }
                phrases = existed.getPhrases();
            } else {
                phrases = packageDto.getPhrases();
            }
            result = super.create(packageDto);
            UUID packageId = result.getId();
            if (CollectionUtils.isNotEmpty(phrases)) {
                phrases = phrases.stream().peek(i -> {
                    i.setPackageId(packageId);
                    i.setId(null);
                }).collect(Collectors.toList());
                phrases = phraseService.create(phrases);
                result.setPhrases(phrases);
            }
        }
        return result;
    }

    @Override
    @Transactional
    public PackageDto update(PackageDto dto) {
        PackageDto result = null;
        if (dto != null) {
            List<PhraseDto> phrases = dto.getPhrases();
            result = super.update(dto);
            UUID packageId = result.getId();
            phraseService.deleteByPackageId(packageId);
            if (CollectionUtils.isNotEmpty(phrases)) {
                phrases = phrases.stream()
                        .collect(Collectors.toMap(PhraseDto::getKey, i -> i, (i, j) -> j))
                        .values()
                        .stream()
                        .peek(i -> i.setPackageId(packageId))
                        .collect(Collectors.toList());
                phrases = phraseService.create(phrases);
                result.setPhrases(phrases);
            }
        }
        return result;
    }

    @Override
    public PackageMapDto getMapByModule(String module) {
        PackageMapDto result = new PackageMapDto();
        if (StringUtils.isNotBlank(module)) {
            List<PackageDto> packages = getByModule(module);
            if (CollectionUtils.isNotEmpty(packages)) {
                Map<UUID, String> isoKeyMap = packages.stream()
                        .collect(Collectors.toMap(PackageDto::getId, PackageDto::getIsoKey));
                Map<String, Map<String, String>> phrases = packages.stream()
                        .filter(i -> CollectionUtils.isNotEmpty(i.getPhrases()))
                        .flatMap(i -> i.getPhrases().stream())
                        .collect(Collectors.groupingBy(
                                PhraseDto::getKey,
                                () -> new TreeMap<>(Comparator.naturalOrder()),
                                Collectors.toMap(
                                        i -> isoKeyMap.get(i.getPackageId()),
                                        PhraseDto::getLabel,
                                        (first, second) -> first)));
                List<LitePackageDto> litePackage = converter.convert(packages, LitePackageDto.class);
                result.setPackages(litePackage);
                result.setPhrases(phrases);
            }
        }
        return result;
    }
}
