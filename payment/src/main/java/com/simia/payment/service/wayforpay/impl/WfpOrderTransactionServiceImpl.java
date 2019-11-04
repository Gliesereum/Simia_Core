package com.simia.payment.service.wayforpay.impl;

import com.simia.payment.model.entity.wayforpay.WfpOrderTransactionEntity;
import com.simia.payment.model.repository.jpa.wayforpay.WfpOrderTransactionRepository;
import com.simia.payment.service.wayforpay.WfpOrderTransactionService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.payment.wayforpay.WfpOrderTransactionDto;
import com.simia.share.common.service.DefaultServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class WfpOrderTransactionServiceImpl extends DefaultServiceImpl<WfpOrderTransactionDto, WfpOrderTransactionEntity> implements WfpOrderTransactionService {

    private static final Class<WfpOrderTransactionDto> DTO_CLASS = WfpOrderTransactionDto.class;
    private static final Class<WfpOrderTransactionEntity> ENTITY_CLASS = WfpOrderTransactionEntity.class;

    private final WfpOrderTransactionRepository wfpOrderTransactionRepository;

    @Autowired
    public WfpOrderTransactionServiceImpl(WfpOrderTransactionRepository wfpOrderTransactionRepository, DefaultConverter defaultConverter) {
        super(wfpOrderTransactionRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.wfpOrderTransactionRepository = wfpOrderTransactionRepository;
    }

    @Override
    public List<WfpOrderTransactionDto> getByOrderId(UUID orderId) {
        List<WfpOrderTransactionEntity> entities = wfpOrderTransactionRepository.getByOrderId(orderId);
        return converter.convert(entities, dtoClass);
    }
}
