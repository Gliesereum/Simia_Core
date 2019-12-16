package com.simia.share.common.model.dto.expert.certificate;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CertificateDto extends AuditableDefaultDto {

    private String description;

    private String logoUrl;

    private String fileUrl;

    private UUID expertId;
}
