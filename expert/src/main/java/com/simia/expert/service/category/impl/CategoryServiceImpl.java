package com.simia.expert.service.category.impl;

import com.simia.expert.model.entity.category.CategoryEntity;
import com.simia.expert.model.repository.jpa.category.CategoryRepository;
import com.simia.expert.service.category.CategoryService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.CustomException;
import com.simia.share.common.model.dto.expert.category.CategoryDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.ExpertExceptionMessage.CATEGORY_NOT_FOUND;


@Slf4j
@Service
public class CategoryServiceImpl extends AuditableServiceImpl<CategoryDto, CategoryEntity> implements CategoryService {

    

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, DefaultConverter defaultConverter) {
        super(categoryRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto create(CategoryDto dto) {
        checkModel(dto);
        return super.create(dto);
    }

    @Override
    public CategoryDto update(CategoryDto dto) {
        checkModel(dto);
        return super.update(dto);
    }

    @Override
    public List<CategoryDto> getAllParents() {
        List<CategoryEntity> entities = categoryRepository.getAllByParentIdIsNullAndObjectState(ObjectState.ACTIVE);
        return converter.convert(entities, dtoClass);
    }

}
