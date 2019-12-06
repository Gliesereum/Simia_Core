package com.simia.share.common.model.dto.account.wallet;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import com.simia.share.common.model.dto.account.enumerated.TransactionResultType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OperationStoryDto extends AuditableDefaultDto {

	private UUID walletFromId;

	private UUID walletToId;
	
	private Double amount;

	private TransactionResultType transactionResultType;
}
