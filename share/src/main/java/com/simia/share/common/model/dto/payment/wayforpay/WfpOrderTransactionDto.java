package com.simia.share.common.model.dto.payment.wayforpay;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simia.share.common.databind.json.LocalDateTimeSecondJsonDeserializer;
import com.simia.share.common.databind.json.LocalDateTimeSecondJsonSerializer;
import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.payment.enumerated.WfpPayTransactionType;
import com.simia.share.common.model.dto.payment.enumerated.WfpTransactionType;
import com.simia.share.common.model.dto.payment.enumerated.WfpTransactionalStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WfpOrderTransactionDto extends DefaultDto {

    private UUID orderId;

    private Double amount;

    private String currency;

    private String authCode;

    private String reason;

    private Integer reasonCode;

    private Double fee;

    private WfpTransactionalStatus transactionStatus;

    private WfpTransactionType transactionType;

    private WfpPayTransactionType payTransactionType;

    @JsonDeserialize(using = LocalDateTimeSecondJsonDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSecondJsonSerializer.class)
    private LocalDateTime transactionDate;

}
