package com.simia.share.common.model.dto.mail;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class MobizonPhoneResponseDto {

    private Integer code;

    private Map<String, Object> data;

    private String message;
}
