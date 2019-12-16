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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContentFullDto extends AuditableDefaultDto {

    private String title;

    private String description;

    private Double price;

    @NonNull
    private Integer duration;

    @NonNull
    private UUID categoryId;

    private UUID promotionId;

    private UUID authorId;

    private ExpertDto expert;

    private String iconUrl;

    private CategoryDto category;

    @NonNull
    private ContentType contentType;

    private LocalDateTime startDate;

    private Boolean isStarting;

    private MediaDto media;

    private PromotionDto promotion;

    private List<CommentDto> comments = new ArrayList<>();

    private List<TagDto> tags = new ArrayList<>();
}
