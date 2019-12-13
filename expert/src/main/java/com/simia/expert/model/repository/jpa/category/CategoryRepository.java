package com.simia.expert.model.repository.jpa.category;

import com.simia.expert.model.entity.category.CategoryEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;

import java.util.List;

public interface CategoryRepository extends AuditableRepository<CategoryEntity> {

    List<CategoryEntity> getAllByParentIdIsNullAndObjectState(ObjectState state);
}