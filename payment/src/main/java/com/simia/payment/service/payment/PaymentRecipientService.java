package com.simia.payment.service.payment;

import com.simia.payment.model.entity.payment.PaymentRecipientEntity;
import com.simia.share.common.model.dto.payment.payment.PaymentRecipientDto;
import com.simia.share.common.service.DefaultService;

/**
 * @author vitalij
 * @version 1.0
 */
public interface PaymentRecipientService extends DefaultService<PaymentRecipientDto, PaymentRecipientEntity> {

    PaymentRecipientDto getByPublicKey(String publicKey);
}
