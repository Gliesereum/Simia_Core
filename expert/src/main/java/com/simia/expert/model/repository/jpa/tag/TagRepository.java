package com.simia.expert.model.repository.jpa.tag;

import com.simia.expert.model.entity.tag.TagEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;

import java.util.List;
import java.util.UUID;


public interface TagRepository extends AuditableRepository <TagEntity> {

    List<TagEntity> getAllByIdInAndObjectStateIn(List<UUID> ids, List<ObjectState> states);
    
    List<TagEntity> getAllByNameInAndObjectStateIn(List<String> names, List<ObjectState> states);
    
    boolean existsByNameAndObjectState(String name, ObjectState objectState);
    
    boolean existsByNameAndIdNotAndObjectState(String name, UUID id, ObjectState objectState);
}
