package com.simia.account.service.wallet.impl;

import com.simia.account.facade.user.UserFacade;
import com.simia.account.model.entity.wallet.OperationStoryEntity;
import com.simia.account.model.repository.jpa.wallet.OperationStoryRepository;
import com.simia.account.service.wallet.OperationStoryService;
import com.simia.account.service.wallet.WalletService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.account.user.PublicUserDto;
import com.simia.share.common.model.dto.account.wallet.OperationStoryDto;
import com.simia.share.common.model.dto.account.wallet.TransactionDto;
import com.simia.share.common.model.dto.account.wallet.WalletDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import com.simia.share.common.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
public class OperationStoryServiceImpl extends AuditableServiceImpl<OperationStoryDto, OperationStoryEntity> implements OperationStoryService {

    private static final Class<OperationStoryDto> DTO_CLASS = OperationStoryDto.class;
    private static final Class<OperationStoryEntity> ENTITY_CLASS = OperationStoryEntity.class;

    private final OperationStoryRepository operationStoryRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    public OperationStoryServiceImpl(OperationStoryRepository operationStoryRepository, DefaultConverter defaultConverter) {
        super(operationStoryRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.operationStoryRepository = operationStoryRepository;
    }

    @Override
    public Page<TransactionDto> getMyTransactions(Long from, Long to, Pageable pageable) {
        SecurityUtil.checkUserByBanStatus();

        WalletDto myWallet = walletService.getByUserId(SecurityUtil.getUserId());

        LocalDateTime fromDate = LocalDateTime.MIN;
        if (from != null) {
            fromDate = Instant.ofEpochMilli(from).atZone(ZoneId.of("UTC")).toLocalDateTime();
        }
        LocalDateTime toDate = LocalDateTime.MAX;
        if (to != null) {
            toDate = Instant.ofEpochMilli(to).atZone(ZoneId.of("UTC")).toLocalDateTime();
        }

        Page<OperationStoryEntity> operationStores = operationStoryRepository.getByWalletFromIdOrWalletToIdAndCreateDateBetweenAndObjectState(
                myWallet.getId(), myWallet.getId(), fromDate, toDate, ObjectState.ACTIVE, pageable);

        List<TransactionDto> transactions = convert(operationStores.getContent());

        setUser(transactions);

        return new PageImpl<>(transactions, pageable, operationStores.getTotalElements());
    }

    private List<TransactionDto> convert(List<OperationStoryEntity> list) {
        List<TransactionDto> result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {

            Map<UUID, WalletDto> walletMap = getWalletMap(list);

            list.forEach(f -> {

                WalletDto walletFrom = walletMap.get(f.getWalletFromId());
                WalletDto walletTo = walletMap.get(f.getWalletToId());

                TransactionDto transaction = new TransactionDto();
                transaction.setAmount(f.getAmount());
                transaction.setOperationDate(f.getCreateDate());
                transaction.setTransactionResultType(f.getTransactionResultType());
                if (walletFrom != null) {
                    transaction.setUserFromId(walletFrom.getUserId());
                }
                if (walletTo != null) {
                    transaction.setUserToId(walletTo.getUserId());
                }
                result.add(transaction);
            });
        }
        return result;
    }

    private void setUser(List<TransactionDto> transactions) {
        if (CollectionUtils.isNotEmpty(transactions)) {
            Set<UUID> userIds = new HashSet<>();

            transactions.forEach(f -> {
                userIds.add(f.getUserFromId());
                userIds.add(f.getUserToId());
            });

            Map<UUID, PublicUserDto> users = findPublicUserMapByIds(new ArrayList<>(userIds));

            transactions.forEach(f -> {
                f.setUserFrom(users.get(f.getUserFromId()));
                f.setUserTo(users.get(f.getUserToId()));
            });
        }
    }

    private Map<UUID, WalletDto> getWalletMap(List<OperationStoryEntity> list) {
        Set<UUID> walletIds = new HashSet<>();
        list.forEach(f -> {
            walletIds.add(f.getWalletFromId());
            walletIds.add(f.getWalletToId());
        });
        return walletService.getMapWalletByIds(new ArrayList<>(walletIds));
    }

    private Map<UUID, PublicUserDto> findPublicUserMapByIds(List<UUID> ids) {
        Map<UUID, PublicUserDto> result = new HashMap<>();
        List<PublicUserDto> publicUsers = userFacade.getPublicUserByIds(ids);
        if (CollectionUtils.isNotEmpty(publicUsers)) {
            result = publicUsers.stream().collect(Collectors.toMap(PublicUserDto::getId, user -> user));
        }
        return result;
    }

    private UUID getWalletId(UUID userId){
        UUID result = null;
        if(userId != null){
            WalletDto wallet = walletService.getByUserId(userId);
            result = wallet.getId();
        }
        return result;
    }

}
