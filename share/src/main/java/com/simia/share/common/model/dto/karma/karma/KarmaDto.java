package com.simia.share.common.model.dto.karma.karma;

import com.simia.share.common.model.dto.DefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class KarmaDto extends DefaultDto {

    private UUID objectId;

    private Long currentValue;

    private Long fixValue;

    private Integer level;
}
