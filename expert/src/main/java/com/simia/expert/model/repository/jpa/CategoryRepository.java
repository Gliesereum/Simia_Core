package com.simia.expert.model.repository.jpa;

import com.simia.expert.model.entity.category.CategoryEntity;
import com.simia.share.common.model.enumerated.ObjectState;

import java.util.List;

public interface CategoryRepository {

    List<CategoryEntity> getAllByParentIdIsNullAndObjectState(ObjectState active);
}
