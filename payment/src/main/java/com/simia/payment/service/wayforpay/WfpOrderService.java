package com.simia.payment.service.wayforpay;

import com.simia.payment.model.entity.wayforpay.WfpOrderEntity;
import com.simia.share.common.model.dto.payment.wayforpay.WfpOrderDto;
import com.simia.share.common.service.DefaultService;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface WfpOrderService extends DefaultService<WfpOrderDto, WfpOrderEntity> {

    WfpOrderDto getByOrderId(UUID orderId);
}    
