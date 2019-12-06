package com.simia.account.service.wallet.impl;

import com.simia.account.model.entity.wallet.WalletEntity;
import com.simia.account.model.repository.jpa.wallet.WalletRepository;
import com.simia.account.service.wallet.OperationStoryService;
import com.simia.account.service.wallet.WalletService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.account.enumerated.TransactionResultType;
import com.simia.share.common.model.dto.account.wallet.OperationStoryDto;
import com.simia.share.common.model.dto.account.wallet.TransactionRequestDto;
import com.simia.share.common.model.dto.account.wallet.WalletDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.simia.share.common.exception.messages.UserExceptionMessage.WALLET_BY_USER_ID_NOT_FOUND;


@Slf4j
@Service
public class WalletServiceImpl extends AuditableServiceImpl<WalletDto, WalletEntity> implements WalletService {

    private static final Class<WalletDto> DTO_CLASS = WalletDto.class;
    private static final Class<WalletEntity> ENTITY_CLASS = WalletEntity.class;

    private final WalletRepository walletRepository;

    @Autowired
    private OperationStoryService operationStoryService;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, DefaultConverter defaultConverter) {
        super(walletRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.walletRepository = walletRepository;
    }

    @Override
    @Transactional
    public TransactionResultType makeTransaction(TransactionRequestDto request) {
        WalletDto walletFrom = getByUserId(request.getUserFromId());
        WalletDto walletTo = getByUserId(request.getUserToId());

        TransactionResultType result = transferMoney(walletFrom, walletTo, request.getAmount());

        OperationStoryDto operationStory = new OperationStoryDto();
        operationStory.setAmount(request.getAmount());
        operationStory.setWalletFromId(walletFrom.getId());
        operationStory.setWalletToId(walletTo.getId());
        operationStory.setTransactionResultType(result);
        operationStoryService.create(operationStory);
        return result;
    }

    @Override
    public WalletDto getByUserId(UUID userId) {
        WalletEntity result = walletRepository.getByUserIdAndObjectState(userId, ObjectState.ACTIVE);
        if (result == null) {
            throw new ClientException(WALLET_BY_USER_ID_NOT_FOUND);
        }
        return converter.convert(result, dtoClass);
    }

    @Override
    public Map<UUID, WalletDto> getMapWalletByIds(List<UUID> ids) {
        Map<UUID, WalletDto> result = new HashMap<>();
        if (CollectionUtils.isNotEmpty(ids)) {
            List<WalletDto> wallets = getByIds(ids, ObjectState.ACTIVE);
            if (CollectionUtils.isNotEmpty(wallets)) {
                result = wallets.stream().collect(Collectors.toMap(WalletDto::getId, wallet -> wallet));
            }
        }
        return result;
    }

    private TransactionResultType transferMoney(WalletDto walletFrom, WalletDto walletTo, Double among) {
        TransactionResultType result = TransactionResultType.SUCCESS;
        //todo add logic move money form one to other account
        return result;
    }
}
