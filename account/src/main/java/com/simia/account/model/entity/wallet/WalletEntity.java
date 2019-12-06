package com.simia.account.model.entity.wallet;

import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "wallet")
public class WalletEntity extends AuditableDefaultEntity {
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "public_key")
	private String publicKey;
	
	@Column(name = "private_key")
	private String privateKey;
	
	@Column(name = "user_id")
	private UUID userId;
	
}
