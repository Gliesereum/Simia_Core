package com.simia.payment.service.liqpay;

import com.simia.share.common.model.dto.payment.liqpay.CheckoutRequestDto;
import com.simia.share.common.model.dto.payment.liqpay.LiqPayResponseDto;

public interface LiqPayCheckoutService {

    String createCheckoutButton(CheckoutRequestDto request);

    void callBack(LiqPayResponseDto response);

    String createCheckoutLinkQrCode(CheckoutRequestDto request);

    byte[] createCheckoutQrCode(CheckoutRequestDto request);

    void sendInvoiceEmail(CheckoutRequestDto request);
}
