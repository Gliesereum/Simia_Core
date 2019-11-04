package com.simia.payment.service.wayforpay;

import com.simia.payment.model.entity.wayforpay.WfpOrderTransactionEntity;
import com.simia.share.common.model.dto.payment.wayforpay.WfpOrderTransactionDto;
import com.simia.share.common.service.DefaultService;

import java.util.List;
import java.util.UUID;

public interface WfpOrderTransactionService extends DefaultService<WfpOrderTransactionDto, WfpOrderTransactionEntity> {

    List<WfpOrderTransactionDto> getByOrderId(UUID orderId);
}
