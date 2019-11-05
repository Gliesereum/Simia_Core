package com.simia.expert.model.repository.jpa.feedback;

import com.simia.expert.model.entity.feedback.FeedBackUserEntity;
import com.simia.share.common.model.dto.expert.feedback.FeedBackSearchDto;

import java.util.List;

/**
 * @author vitalij
 * @version 1.0
 */
public interface FeedBackSearchRepository {

    List<FeedBackUserEntity> getBySearch(FeedBackSearchDto search);
}
