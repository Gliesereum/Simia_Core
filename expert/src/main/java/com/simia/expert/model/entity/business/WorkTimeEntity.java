package com.simia.expert.model.entity.business;

import com.simia.share.common.model.dto.expert.enumerated.WorkTimeType;
import com.simia.share.common.model.entity.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "work_time")
public class WorkTimeEntity extends DefaultEntity {

    @Column(name = "from_time")
    private LocalTime from;

    @Column(name = "to_time")
    private LocalTime to;

    @Column(name = "object_id")
    private UUID objectId;

    @Column(name = "is_work")
    private Boolean isWork;

    @Column(name = "business_category_id")
    private UUID businessCategoryId;

    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private WorkTimeType type;

}
