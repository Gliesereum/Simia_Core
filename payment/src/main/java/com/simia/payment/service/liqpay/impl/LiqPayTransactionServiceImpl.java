package com.simia.payment.service.liqpay.impl;

import com.simia.payment.model.liqpay.LiqPayTransactionEntity;
import com.simia.payment.model.repository.jpa.liqpay.LiqPayTransactionRepository;
import com.simia.payment.service.liqpay.LiqPayTransactionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.payment.liqpay.LiqPayTransactionDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
public class LiqPayTransactionServiceImpl extends DefaultServiceImpl<LiqPayTransactionDto, LiqPayTransactionEntity> implements LiqPayTransactionService {

    private static final Class<LiqPayTransactionDto> DTO_CLASS = LiqPayTransactionDto.class;
    private static final Class<LiqPayTransactionEntity> ENTITY_CLASS = LiqPayTransactionEntity.class;

    private final LiqPayTransactionRepository liqPayTransactionRepository;

    @Autowired
    public LiqPayTransactionServiceImpl(LiqPayTransactionRepository liqPayTransactionRepository, DefaultConverter defaultConverter) {
        super(liqPayTransactionRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.liqPayTransactionRepository = liqPayTransactionRepository;
    }

    @Override
    public LiqPayTransactionDto getByOrderId(UUID orderId) {
        LiqPayTransactionEntity entity = liqPayTransactionRepository.getByOrderId(orderId);
        return converter.convert(entity, dtoClass);
    }

}
