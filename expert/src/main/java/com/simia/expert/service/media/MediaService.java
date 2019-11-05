package com.simia.expert.service.media;

import com.simia.expert.model.entity.media.MediaEntity;
import com.simia.share.common.model.dto.expert.media.MediaDto;
import com.simia.share.common.model.dto.expert.media.MediaListUpdateDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface MediaService extends DefaultService<MediaDto, MediaEntity> {

    List<MediaDto> getByObjectId(UUID objectId);

    Map<UUID, List<MediaDto>> getMapByObjectIds(List<UUID> objectIds);

   void delete(UUID id, UUID objectId);
    
    List<MediaDto> updateList(MediaListUpdateDto medias);
}
