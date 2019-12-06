package com.simia.account.service.wallet;

import com.simia.account.model.entity.wallet.WalletEntity;
import com.simia.share.common.model.dto.account.enumerated.TransactionResultType;
import com.simia.share.common.model.dto.account.wallet.TransactionRequestDto;
import com.simia.share.common.model.dto.account.wallet.WalletDto;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface WalletService extends AuditableService<WalletDto, WalletEntity> {

    TransactionResultType makeTransaction(TransactionRequestDto request);

    WalletDto getByUserId(UUID userId);

    Map<UUID, WalletDto> getMapWalletByIds(List<UUID> ids);
}
