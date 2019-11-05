package com.simia.share.common.model.dto.expert.business;

import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.account.user.PublicUserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LiteWorkerDto extends DefaultDto {

    private UUID userId;

    private String position;

    private UUID workingSpaceId;

    private UUID businessId;

    private PublicUserDto user;

}
