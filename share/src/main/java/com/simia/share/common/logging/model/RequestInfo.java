package com.simia.share.common.logging.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@ToString
@NoArgsConstructor
public class RequestInfo {

    private Long startTime;

    private Long endTime;

    private Long durationMillis;

    private String requestBody;

    private int requestLength;

    private String responseBody;

    private int responseLength;

    private boolean isError;

    private String errorMessage;

    private String method;

    private String uri;

    private Integer httpStatus;

    private Map<String, String> headers;

    private Map<String, String[]> parameters;
}
