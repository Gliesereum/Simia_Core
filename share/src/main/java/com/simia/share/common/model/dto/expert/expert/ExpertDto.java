package com.simia.share.common.model.dto.expert.expert;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExpertDto extends AuditableDefaultDto {
	
	private UUID userId;
	
	private String description;
	
	private String title;
	
	private String additional;
}
