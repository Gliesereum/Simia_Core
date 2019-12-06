package com.simia.share.common.model.dto.expert.subscriber;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import com.simia.share.common.model.dto.expert.content.ContentFullDto;
import com.simia.share.common.model.dto.expert.enumerated.ContentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SubscriberDto extends AuditableDefaultDto {

    private UUID userId;

    private ContentType contentType;

    private UUID contentId;

    private ContentFullDto content;
}
