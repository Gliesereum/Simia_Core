package com.simia.share.common.model.dto.expert.promo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simia.share.common.databind.json.LocalDateTimeJsonDeserializer;
import com.simia.share.common.databind.json.LocalDateTimeJsonSerializer;
import com.simia.share.common.model.dto.AuditableDefaultDto;
import com.simia.share.common.model.dto.expert.category.CategoryDto;
import com.simia.share.common.model.dto.expert.enumerated.PromoType;
import com.simia.share.common.model.dto.expert.expert.ExpertDto;
import com.simia.share.common.model.dto.expert.media.MediaDto;
import com.simia.share.common.model.dto.expert.speacker.SpeakerDto;
import com.simia.share.common.model.dto.expert.tag.TagDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PromotionFullDto extends AuditableDefaultDto {

    private String title;

    private String description;

    private UUID objectId;

    private UUID authorId;

    private UUID categoryId;

    private ExpertDto expert;

    private Double price;

    private Integer followerCount;

    private Integer interestedCount;

    private String logoUrl;

    private PromoType promoType;

    private CategoryDto category;

    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    private LocalDateTime startDate;

    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    private LocalDateTime finishDate;

    private GiveAwayDto giveAway;

    private List<MediaDto> media = new ArrayList<>();

    private List<SpeakerDto> speakers = new ArrayList<>();

    private List<TagDto> tags = new ArrayList<>();
}
