package com.simia.share.common.model.dto.expert.record;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simia.share.common.databind.json.LocalDateTimeJsonDeserializer;
import com.simia.share.common.databind.json.LocalDateTimeJsonSerializer;
import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.expert.business.BaseBusinessDto;
import com.simia.share.common.model.dto.expert.enumerated.PayType;
import com.simia.share.common.model.dto.expert.enumerated.StatusPay;
import com.simia.share.common.model.dto.expert.enumerated.StatusProcess;
import com.simia.share.common.model.dto.expert.enumerated.StatusRecord;
import com.simia.share.common.model.dto.expert.service.PackageDto;
import com.simia.share.common.model.dto.expert.service.ServicePriceDto;
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
public class AbstractRecordDto extends DefaultDto {

    private UUID clientId;

    private UUID targetId;

    private UUID packageId;

    private UUID businessId;

    private Integer price;

    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    private LocalDateTime begin;

    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    private LocalDateTime finish;

    private String description;

    private String canceledDescription;

    private PackageDto packageDto;

    private BaseBusinessDto business;

    private StatusPay statusPay;

    private PayType payType;

    private StatusProcess statusProcess;

    private StatusRecord statusRecord;

    private UUID businessCategoryId;

    private boolean notificationSend;

    private Integer recordNumber;

    private List<ServicePriceDto> services = new ArrayList<>();

    private List<UUID> servicesIds = new ArrayList<>();

}
