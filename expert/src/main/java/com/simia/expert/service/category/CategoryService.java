package com.simia.expert.service.category;

import com.simia.expert.model.entity.category.CategoryEntity;
import com.simia.share.common.model.dto.expert.category.CategoryDto;
import com.simia.share.common.service.auditable.AuditableService;

public interface CategoryService extends AuditableService<CategoryDto, CategoryEntity> {
}