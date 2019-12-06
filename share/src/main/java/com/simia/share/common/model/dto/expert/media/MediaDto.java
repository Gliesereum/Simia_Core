package com.simia.share.common.model.dto.expert.media;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import com.simia.share.common.model.dto.expert.enumerated.MediaType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MediaDto extends AuditableDefaultDto {
	
	@NotNull
	private UUID objectId;
	
	private String title;
	
	private String description;
	
	@NotEmpty
	@Size(min = 8)
	private String url;
	
	@NotNull
	private MediaType mediaType;
}
