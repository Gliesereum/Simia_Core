package com.simia.share.common.model.dto.expert.record;

import com.simia.share.common.model.dto.expert.client.ClientDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 * @since 12/7/18
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseRecordDto extends AbstractRecordDto {

    private UUID workingSpaceId;

    private boolean specifiedWorkingSpace;

    private UUID workerId;

    private ClientDto client;

}
