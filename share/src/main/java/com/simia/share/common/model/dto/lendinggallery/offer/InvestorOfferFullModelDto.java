package com.simia.share.common.model.dto.lendinggallery.offer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simia.share.common.databind.json.LocalDateTimeJsonDeserializer;
import com.simia.share.common.databind.json.LocalDateTimeJsonSerializer;
import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.account.user.UserDto;
import com.simia.share.common.model.dto.lendinggallery.artbond.ArtBondDto;
import com.simia.share.common.model.dto.lendinggallery.customer.CustomerDto;
import com.simia.share.common.model.dto.lendinggallery.enumerated.OfferStateType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InvestorOfferFullModelDto extends DefaultDto {

    private Integer sumInvestment;

    private Integer stockCount;

    private UUID customerId;

    private UUID artBondId;

    private OfferStateType stateType;

    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    private LocalDateTime create;

    private CustomerDto customer;

    private UserDto user;

    private ArtBondDto artBond;

    private List<OfferCommentDto> comments = new ArrayList<>();
}
