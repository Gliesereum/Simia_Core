package com.simia.payment.service.wayforpay;

import com.simia.share.common.model.dto.payment.wayforpay.*;

/**
 * @author vitalij
 * @version 1.0
 */
public interface WfpPaymentService {

    WfpPurchaseResponseModel purchase(WfpPurchaseRequestModel model) throws RuntimeException;

   // void purchaseResponse(Map<String, String> response);
}
