package com.simia.share.common.model.dto.expert.content;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PackageContentDto extends AuditableDefaultDto {

    private UUID packageId;

    private UUID contentId;
}
