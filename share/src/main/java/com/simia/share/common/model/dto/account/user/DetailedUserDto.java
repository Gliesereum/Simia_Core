package com.simia.share.common.model.dto.account.user;

import com.simia.share.common.model.dto.DefaultDto;
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
public class DetailedUserDto extends DefaultDto {
	
	private String phone;
	
	private String email;
	
	private UserDto user;
}
