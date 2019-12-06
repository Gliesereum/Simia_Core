package com.simia.account.model.entity.wallet;

import com.simia.share.common.model.dto.account.enumerated.TransactionResultType;
import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "operation_story")
public class OperationStoryEntity extends AuditableDefaultEntity {
	
	@Column(name = "wallet_from_id")
	private UUID walletFromId;

	@Column(name = "wallet_to_id")
	private UUID walletToId;
	
	@Column(name = "amount")
	private Double amount;

	@Column(name = "transaction_result_type")
	@Enumerated(EnumType.STRING)
	private TransactionResultType transactionResultType;
	
}
