package com.simia.expert.service.category;

import com.simia.expert.model.entity.category.CategoryEntity;
import com.simia.share.common.model.dto.expert.category.CategoryDto;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.List;

import java.util.List;
import java.util.UUID;

public interface CategoryService extends AuditableService<CategoryDto, CategoryEntity> {

    List<CategoryDto> getAllParents();

    List<UUID> getIdsChildren(UUID id);

    List<CategoryDto> getAllParents();
}