package com.simia.expert.model.entity.record;

import com.simia.share.common.model.dto.expert.enumerated.PayType;
import com.simia.share.common.model.dto.expert.enumerated.StatusPay;
import com.simia.share.common.model.dto.expert.enumerated.StatusProcess;
import com.simia.share.common.model.dto.expert.enumerated.StatusRecord;
import com.simia.share.common.model.entity.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class AbstractRecordEntity extends DefaultEntity {

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "target_id")
    private UUID targetId;

    @Column(name = "package_id")
    private UUID packageId;

    @Column(name = "business_id")
    private UUID businessId;

    @Column(name = "price")
    private Integer price;

    @Column(name = "begin")
    private LocalDateTime begin;

    @Column(name = "finish")
    private LocalDateTime finish;

    @Column(name = "description")
    private String description;

    @Column(name = "canceled_description")
    private String canceledDescription;

    @Column(name = "status_pay")
    @Enumerated(EnumType.STRING)
    private StatusPay statusPay;

    @Column(name = "pay_type")
    @Enumerated(EnumType.STRING)
    private PayType payType;

    @Column(name = "status_process")
    @Enumerated(EnumType.STRING)
    private StatusProcess statusProcess;

    @Column(name = "status_record")
    @Enumerated(EnumType.STRING)
    private StatusRecord statusRecord;

    @Column(name = "business_category_id")
    private UUID businessCategoryId;

    @Column(name = "notification_send")
    private boolean notificationSend;

    @Column(name = "record_number")
    private Integer recordNumber;

}
