package com.simia.mail.service.mail;

import com.simia.mail.model.entity.MailStateEntity;
import com.simia.share.common.model.dto.mail.MailStateDto;
import com.simia.share.common.service.DefaultService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author vitalij
 * @version 1.0
 */
public interface MailStateService extends DefaultService<MailStateDto, MailStateEntity> {

    List<MailStateDto> getByMessageStatusAndDateAfter(String status, LocalDateTime date);

}    
