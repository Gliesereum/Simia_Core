package com.simia.share.common.model.dto.karma.administrator;

import com.simia.share.common.model.dto.account.user.PublicUserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DetailedBusinessAdministratorDto extends BusinessAdministratorDto {

    private PublicUserDto user;
}
