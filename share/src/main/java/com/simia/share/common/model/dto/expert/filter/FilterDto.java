package com.simia.share.common.model.dto.expert.filter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simia.share.common.databind.json.description.DescriptionJsonDeserializer;
import com.simia.share.common.databind.json.description.DescriptionJsonSerializer;
import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.expert.filter.descriptions.FilterDescriptionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
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
public class FilterDto extends DefaultDto {

    @NotEmpty
    private String value;

    @NotEmpty
    private String title;

    private UUID businessCategoryId;

    private List<FilterAttributeDto> attributes = new ArrayList<>();

    @JsonDeserialize(using = DescriptionJsonDeserializer.class)
    @JsonSerialize(using = DescriptionJsonSerializer.class)
    private List<FilterDescriptionDto> descriptions = new ArrayList<>();
}
