package com.simia.share.common.model.dto.expert.record;

import com.simia.share.common.model.dto.expert.business.BaseBusinessDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class RecordNotificationDto {
	
	private BaseRecordDto record;
	
	private BaseBusinessDto business;
	
	private List<UUID> userIds;
}
