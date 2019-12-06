package com.simia.share.common.model.dto.account.wallet;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WalletDto extends AuditableDefaultDto {
	
	private String address;
	
	private String publicKey;
	
	private String privateKey;
	
	private UUID userId;
}
