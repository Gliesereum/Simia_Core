package com.simia.expert.service.feedback;

import com.simia.expert.model.entity.feedback.FeedBackUserEntity;
import com.simia.share.common.model.dto.expert.feedback.FeedBackSearchDto;
import com.simia.share.common.model.dto.expert.feedback.FeedBackUserDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface FeedBackUserService extends DefaultService<FeedBackUserDto, FeedBackUserEntity> {

    void recordFeedback(UUID recordId, String comment, Integer mark);

    List<FeedBackUserDto> getAllBySearch(FeedBackSearchDto search);

    FeedBackUserDto getByRecord(UUID recordId);
}
