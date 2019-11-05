package com.simia.share.common.model.dto.expert.comment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class RatingDto {

    private BigDecimal rating;

    private Integer count;
}
