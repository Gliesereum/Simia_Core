package com.simia.expert.service.comment;

import com.simia.expert.model.entity.comment.CommentEntity;
import com.simia.share.common.model.dto.expert.comment.CommentDto;
import com.simia.share.common.model.dto.expert.comment.CommentFullDto;
import com.simia.share.common.model.dto.expert.comment.RatingDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface CommentService extends DefaultService<CommentDto, CommentEntity> {

    List<CommentDto> findByObjectId(UUID objectId);

    List<CommentFullDto> findFullByObjectId(UUID objectId);

    Map<UUID, List<CommentFullDto>> getMapFullByObjectIds(List<UUID> objectIds);

    CommentFullDto findByObjectIdForCurrentUser(UUID objectId);

    CommentDto addComment(UUID objectId, UUID userId, CommentDto comment);

    CommentDto updateComment(UUID userId, CommentDto comment);

    CommentDto deleteComment(UUID commentId, UUID userId);

    RatingDto getRating(UUID objectId);

    Map<UUID, RatingDto> getRatings(List<UUID> objectIds);
}
