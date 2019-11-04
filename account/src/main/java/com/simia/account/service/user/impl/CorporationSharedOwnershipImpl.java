package com.simia.account.service.user.impl;

import com.simia.account.model.entity.CorporationSharedOwnershipEntity;
import com.simia.account.model.repository.jpa.user.CorporationSharedOwnershipRepository;
import com.simia.account.service.user.CorporationSharedOwnershipService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.account.user.CorporationSharedOwnershipDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author vitalij
 */
@Slf4j
@Service
public class CorporationSharedOwnershipImpl extends DefaultServiceImpl<CorporationSharedOwnershipDto, CorporationSharedOwnershipEntity> implements CorporationSharedOwnershipService {

    private static final Class<CorporationSharedOwnershipDto> DTO_CLASS = CorporationSharedOwnershipDto.class;
    private static final Class<CorporationSharedOwnershipEntity> ENTITY_CLASS = CorporationSharedOwnershipEntity.class;

    private final CorporationSharedOwnershipRepository corporationSharedOwnershipRepository;

    public CorporationSharedOwnershipImpl(CorporationSharedOwnershipRepository repository, DefaultConverter defaultConverter) {
        super(repository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.corporationSharedOwnershipRepository = repository;
    }

    @Override
    public List<CorporationSharedOwnershipDto> getAllByUserId(UUID id) {
        List<CorporationSharedOwnershipEntity> entities = corporationSharedOwnershipRepository.findByOwnerId(id);
        return converter.convert(entities, dtoClass);
    }

    @Override
    public List<CorporationSharedOwnershipDto> getAllByCorporationOwnerId(UUID id) {
        List<CorporationSharedOwnershipEntity> entities = corporationSharedOwnershipRepository.findByCorporationOwnerId(id);
        return converter.convert(entities, dtoClass);
    }

    @Override
    public List<UUID> getAllCorporationIdByUserId(UUID id) {
        List<UUID> result = null;
        List<CorporationSharedOwnershipDto> founded = getAllByUserId(id);
        if (CollectionUtils.isNotEmpty(founded)) {
            result = founded.stream()
                    .map(CorporationSharedOwnershipDto::getCorporationId)
                    .collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public Map<UUID, List<UUID>> getAllCorporationIdByUserIds(List<UUID> userIds) {
        Map<UUID, List<UUID>> result = null;
        List<CorporationSharedOwnershipDto> ownerships = getAllByUserIds(userIds);
        if (CollectionUtils.isNotEmpty(ownerships)) {
            result = ownerships.stream()
                    .collect(Collectors.groupingBy(
                            CorporationSharedOwnershipDto::getOwnerId,
                            Collectors.mapping(CorporationSharedOwnershipDto::getCorporationId, Collectors.toList())));
        }
        return result;
    }

    @Override
    public List<CorporationSharedOwnershipDto> getAllByUserIds(List<UUID> userIds) {
        List<CorporationSharedOwnershipDto> result = null;
        if (CollectionUtils.isNotEmpty(userIds)) {
            List<CorporationSharedOwnershipEntity> entities = corporationSharedOwnershipRepository.findAllByOwnerIdIn(userIds);
            result = converter.convert(entities, dtoClass);
        }
        return result;
    }
}
