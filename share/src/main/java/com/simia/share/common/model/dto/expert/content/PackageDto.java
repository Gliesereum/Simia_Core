package com.simia.share.common.model.dto.expert.content;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import com.simia.share.common.model.dto.expert.category.CategoryDto;
import com.simia.share.common.model.dto.expert.comment.CommentDto;
import com.simia.share.common.model.dto.expert.enumerated.ContentType;
import com.simia.share.common.model.dto.expert.expert.ExpertDto;
import com.simia.share.common.model.dto.expert.promo.PromotionDto;
import com.simia.share.common.model.dto.expert.tag.TagDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PackageDto extends AuditableDefaultDto {

    @NonNull
    private String title;

    private String description;

    @Min(0)
    private Double price;

    @NonNull
    private UUID categoryId;

    private UUID promotionId;

    private UUID authorId;

    private String iconUrl;

    private CategoryDto category;

    private List<ContentDto> contents = new ArrayList<>();

    private List<UUID> contentIds = new ArrayList<>();
}
