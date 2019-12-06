package com.simia.account.model.repository.jpa.wallet;

import com.simia.account.model.entity.wallet.WalletEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;

import java.util.UUID;

public interface WalletRepository extends AuditableRepository<WalletEntity> {

    WalletEntity getByUserIdAndObjectState(UUID userId, ObjectState objectState);
}
