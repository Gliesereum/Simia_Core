package com.simia.share.common.model.dto.karma.record;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simia.share.common.databind.json.LocalDateTimeJsonDeserializer;
import com.simia.share.common.databind.json.LocalDateTimeJsonSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class RequestLiteRecordDto{

    private String comment;

    private UUID servicePriceId;

    @NotNull
    private UUID businessId;

    @NotNull
    private String phone;

    @NotNull
    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    private LocalDateTime dateTime;
}
