package com.simia.mail.service.feedback;

import com.simia.mail.model.entity.FeedBackUserEntity;
import com.simia.share.common.model.dto.mail.FeedBackUserDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface FeedBackUserService extends DefaultService<FeedBackUserDto, FeedBackUserEntity> {
    
    List<FeedBackUserDto> getAllByAppId(UUID id);

    List<FeedBackUserDto> createList(List<FeedBackUserDto> dtos);
}    
