package com.simia.expert.service.tag;

import com.simia.expert.model.entity.tag.ObjectTagEntity;
import com.simia.share.common.model.dto.expert.tag.ObjectTagDto;
import com.simia.share.common.model.dto.expert.tag.TagDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ObjectTagService extends DefaultService<ObjectTagDto, ObjectTagEntity> {
   
    List<TagDto> addTag(UUID tagId, UUID objectId);
    
    List<TagDto> saveTags(List<UUID> tagIds, UUID objectId);

    List<TagDto> removeTag(UUID tagId, UUID objectId);

    List<UUID> getTagIdsByObjectId(UUID objectId);

    void deleteByObjectId(UUID objectId);
    
    List<TagDto> getByObjectId(UUID objectId);

    Map<UUID, List<TagDto>> getMapByObjectIds(List<UUID> ids);
}
