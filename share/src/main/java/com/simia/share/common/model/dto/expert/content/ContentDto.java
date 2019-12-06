package com.simia.share.common.model.dto.expert.content;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import com.simia.share.common.model.dto.expert.category.CategoryDto;
import com.simia.share.common.model.dto.expert.comment.CommentDto;
import com.simia.share.common.model.dto.expert.enumerated.ContentType;
import com.simia.share.common.model.dto.expert.expert.ExpertDto;
import com.simia.share.common.model.dto.expert.media.MediaDto;
import com.simia.share.common.model.dto.expert.promo.PromotionDto;
import com.simia.share.common.model.dto.expert.tag.TagDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.*;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContentDto extends AuditableDefaultDto {

    private String title;

    private String description;

    @Min(0)
    private Double price;

    @NonNull
    private Integer duration;

    @NonNull
    private UUID categoryId;

    private UUID promotionId;

    private UUID authorId;

    private String iconUrl;

    @NonNull
    private ContentType contentType;

    private LocalDateTime startDate;

    private Boolean isStarting;

    private MediaDto media;

}
