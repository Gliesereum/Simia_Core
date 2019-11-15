package com.simia.expert.service.content;

import com.simia.expert.model.entity.content.ContentEntity;
import com.simia.share.common.model.dto.expert.content.ContentDto;
import com.simia.share.common.service.auditable.AuditableService;

public interface ContentService extends AuditableService<ContentDto, ContentEntity> {
}