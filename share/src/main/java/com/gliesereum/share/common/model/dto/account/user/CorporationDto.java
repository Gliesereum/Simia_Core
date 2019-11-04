package com.gliesereum.share.common.model.dto.account.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gliesereum.share.common.databind.json.LocalDateTimeJsonDeserializer;
import com.gliesereum.share.common.databind.json.LocalDateTimeJsonSerializer;
import com.gliesereum.share.common.model.dto.AuditableDefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author vitalij
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CorporationDto extends AuditableDefaultDto {

    @Size(min = 2)
    @NotEmpty
    private String name;

    private String description;

    private String phone;

    private String index;

    private String country;

    private String region;

    private String city;

    private String street;

    private String buildingNumber;

    private String officeNumber;

    private String logoUrl;

    private String coverUrl;

    private String rcNumber;

    private String companyType;

    private String businessActivity;

    private String placeIncorporation;

    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    private LocalDateTime dateIncorporation;

    private Boolean kycApproved;

    private List<CorporationSharedOwnershipDto> corporationSharedOwnerships;

    private List<CorporationEmployeeDto> corporationEmployees;
}
