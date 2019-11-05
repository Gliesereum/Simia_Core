package com.simia.expert.service.service;

import com.simia.expert.model.entity.service.PackageEntity;
import com.simia.share.common.model.dto.expert.service.LitePackageDto;
import com.simia.share.common.model.dto.expert.service.PackageDto;
import com.simia.share.common.model.dto.expert.tag.TagDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface PackageService extends DefaultService<PackageDto, PackageEntity> {

    List<PackageDto> getByBusinessId(UUID id);

    Map<UUID, List<PackageDto>> getMapByBusinessIds(List<UUID> businessIds);
    
    Map<UUID, List<LitePackageDto>> getLiteMapByBusinessIds(List<UUID> businessIds);
    
    Map<UUID, LitePackageDto> getMapByIds(List<UUID> packageIds);

    PackageDto getByIdIgnoreState(UUID id);

    List<LitePackageDto> getLitePackageByBusinessId(UUID id);

    LitePackageDto getLiteById(UUID id);
    
    List<LitePackageDto> getLiteByIds(List<UUID> ids);

    List<TagDto> addTag(UUID tagId, UUID idPackage);

    List<TagDto> getTags(UUID idPackage);

    List<TagDto> saveTags(List<UUID> tagId, UUID idPackage);

    List<TagDto> removeTag(UUID tagId, UUID idPackage);
}
