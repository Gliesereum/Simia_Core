package com.simia.expert.model.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class WorkTimeDocument {

    @Field(type = FieldType.Date, format = DateFormat.hour_minute)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime from;

    @Field(type = FieldType.Date, format = DateFormat.hour_minute)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime to;

    @Field(type = FieldType.Keyword)
    private String objectId;

    @Field(type = FieldType.Boolean)
    private Boolean isWork;

    @Field(type = FieldType.Keyword)
    private UUID businessCategoryId;

    @Field(type = FieldType.Keyword)
    private DayOfWeek dayOfWeek;
}
