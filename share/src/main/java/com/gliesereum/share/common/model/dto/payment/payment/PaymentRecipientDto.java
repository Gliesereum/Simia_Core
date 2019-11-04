package com.gliesereum.share.common.model.dto.payment.payment;

import com.gliesereum.share.common.model.dto.DefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentRecipientDto extends DefaultDto {

    private UUID objectId;

    private String publicKey;

    private String privateKey;

}
