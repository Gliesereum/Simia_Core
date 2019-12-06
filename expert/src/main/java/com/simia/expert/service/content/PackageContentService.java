package com.simia.expert.service.content;

import com.simia.expert.model.entity.content.PackageContentEntity;
import com.simia.share.common.model.dto.expert.content.PackageContentDto;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.List;
import java.util.UUID;

public interface PackageContentService extends AuditableService<PackageContentDto, PackageContentEntity> {

    List<PackageContentDto> getByPackageId(UUID id);
}