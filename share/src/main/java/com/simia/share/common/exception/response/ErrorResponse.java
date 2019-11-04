package com.simia.share.common.exception.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simia.share.common.databind.json.LocalDateTimeJsonDeserializer;
import com.simia.share.common.databind.json.LocalDateTimeJsonSerializer;
import com.simia.share.common.exception.messages.ExceptionMessage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class ErrorResponse {

    private int code;

    private String message;

    private String path;

    private Object additional;

    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    private LocalDateTime timestamp;

    @JsonIgnore
    private int httpCode;

    public ErrorResponse(ExceptionMessage exceptionMessage) {
        this.code = exceptionMessage.getErrorCode();
        this.message = exceptionMessage.getMessage();
        this.httpCode = exceptionMessage.getHttpCode();
    }
}
