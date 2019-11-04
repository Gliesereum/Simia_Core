package com.simia.share.common.model.dto.lendinggallery.offer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simia.share.common.databind.json.LocalDateTimeJsonDeserializer;
import com.simia.share.common.databind.json.LocalDateTimeJsonSerializer;
import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.lendinggallery.enumerated.OfferStateType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BorrowerOfferDto extends DefaultDto {

    private UUID customerId;

    private String description;

    private OfferStateType stateType;

    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    private LocalDateTime create;
}
