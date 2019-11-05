package com.simia.share.common.model.dto.expert.business;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import com.simia.share.common.model.dto.expert.business.descriptions.BusinessDescriptionDto;
import com.simia.share.common.model.dto.expert.comment.CommentFullDto;
import com.simia.share.common.model.dto.expert.comment.RatingDto;
import com.simia.share.common.model.dto.expert.media.MediaDto;
import com.simia.share.common.model.dto.expert.record.BaseRecordDto;
import com.simia.share.common.model.dto.expert.service.PackageDto;
import com.simia.share.common.model.dto.expert.service.ServicePriceDto;
import com.simia.share.common.model.dto.expert.tag.TagDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class BusinessFullModel extends AuditableDefaultDto {

    private String name;

    private UUID corporationId;

    private UUID businessId;

    private String description;

    private String logoUrl;

    private String coverUrl;
    
    private String siteUrl;
    
    private String videoUrl;

    private String address;

    private String phone;

    private String addPhone;

    private Double latitude;

    private Double longitude;

    private Integer timeZone;

    private RatingDto rating;

    private UUID businessCategoryId;

    private BusinessCategoryDto businessCategory;

    private boolean businessVerify;

    private List<WorkTimeDto> workTimes = new ArrayList<>();

    private List<WorkingSpaceDto> spaces = new ArrayList<>();

    private List<WorkerDto> workers = new ArrayList<>();

    private List<BusinessDescriptionDto> descriptions = new ArrayList<>();

    private List<ServicePriceDto> servicePrices = new ArrayList<>();

    private List<PackageDto> packages = new ArrayList<>();

    private List<MediaDto> media = new ArrayList<>();

    private List<CommentFullDto> comments = new ArrayList<>();

    private List<BaseRecordDto> records = new ArrayList<>();

    private List<TagDto> tags = new ArrayList<>();
}
