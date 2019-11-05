package com.simia.expert.service.tag;

import com.simia.expert.model.entity.tag.TagEntity;
import com.simia.share.common.model.dto.expert.tag.TagDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TagService extends AuditableService<TagDto, TagEntity> {

    Map<UUID, TagDto> getMapByIds(List<UUID> ids, List<ObjectState> states);
    
    List<TagDto> getTagsByName(List<String> names, List<ObjectState> states);
    
    List<TagDto> getByIds(List<UUID> tagIds);
}
