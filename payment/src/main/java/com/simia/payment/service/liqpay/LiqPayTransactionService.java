package com.simia.payment.service.liqpay;

import com.simia.payment.model.liqpay.LiqPayTransactionEntity;
import com.simia.share.common.model.dto.payment.liqpay.LiqPayTransactionDto;
import com.simia.share.common.service.DefaultService;

import java.util.UUID;

public interface LiqPayTransactionService extends DefaultService<LiqPayTransactionDto, LiqPayTransactionEntity> {

    LiqPayTransactionDto getByOrderId(UUID orderId);
}
