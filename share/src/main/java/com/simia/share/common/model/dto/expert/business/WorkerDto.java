package com.simia.share.common.model.dto.expert.business;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import com.simia.share.common.model.dto.account.user.PublicUserDto;
import com.simia.share.common.model.dto.expert.comment.CommentFullDto;
import com.simia.share.common.model.dto.expert.comment.RatingDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WorkerDto extends AuditableDefaultDto {

    private UUID userId;

    private String position;

    private UUID workingSpaceId;

    private UUID businessId;

    private UUID corporationId;

    private PublicUserDto user;

    private RatingDto rating;

    private List<CommentFullDto> comments = new ArrayList<>();

    private List<WorkTimeDto> workTimes = new ArrayList<>();

}
