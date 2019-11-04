package com.simia.share.common.model.dto.account.user;

import com.simia.share.common.model.dto.DefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author vitalij
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserEmailDto extends DefaultDto {

    private String email;

    private UUID userId;
}
