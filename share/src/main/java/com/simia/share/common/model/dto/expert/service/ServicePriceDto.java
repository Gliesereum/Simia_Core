package com.simia.share.common.model.dto.expert.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simia.share.common.databind.json.description.DescriptionJsonDeserializer;
import com.simia.share.common.databind.json.description.DescriptionJsonSerializer;
import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.expert.filter.FilterAttributeDto;
import com.simia.share.common.model.dto.expert.service.descriptions.ServicePriceDescriptionDto;
import com.simia.share.common.model.dto.expert.tag.TagDto;
import com.simia.share.common.model.enumerated.ObjectState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
public class ServicePriceDto extends DefaultDto {

    private String name;

    private String description;

    @NotNull
    @Min(0)
    private Integer price;

    private UUID serviceId;

    private UUID businessId;

    private ServiceDto service;

    private ObjectState objectState;

    @NotNull
    @Max(1440)
    @Min(0)
    private Integer duration;

    @JsonDeserialize(using = DescriptionJsonDeserializer.class)
    @JsonSerialize(using = DescriptionJsonSerializer.class)
    private List<ServicePriceDescriptionDto> descriptions = new ArrayList<>();

    private List<ServiceClassDto> serviceClass = new ArrayList<>();

    private List<FilterAttributeDto> attributes = new ArrayList<>();

    private List<TagDto> tags = new ArrayList<>();
}
