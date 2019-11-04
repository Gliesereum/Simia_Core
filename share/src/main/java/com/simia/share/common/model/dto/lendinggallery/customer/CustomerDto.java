package com.simia.share.common.model.dto.lendinggallery.customer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simia.share.common.databind.json.LocalDateTimeJsonDeserializer;
import com.simia.share.common.databind.json.LocalDateTimeJsonSerializer;
import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.lendinggallery.enumerated.BorrowerType;
import com.simia.share.common.model.dto.lendinggallery.enumerated.CustomerType;
import com.simia.share.common.model.dto.lendinggallery.enumerated.InvestorType;
import com.simia.share.common.model.dto.lendinggallery.enumerated.OriginFunds;
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
public class CustomerDto extends DefaultDto {

    private UUID userId;

    private String name;

    private String placeBirth;

    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    private LocalDateTime dateBirth;

    private String nationality;

    private String passport;

    private String position;

    private Integer amountInvestment;

    private OriginFunds originFunds;

    private CustomerType customerType;

    private InvestorType investorType;

    private BorrowerType borrowerType;
}
