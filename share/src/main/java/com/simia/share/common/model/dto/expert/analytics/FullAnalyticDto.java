package com.simia.share.common.model.dto.expert.analytics;

import com.simia.share.common.model.dto.expert.business.LiteWorkerDto;
import com.simia.share.common.model.dto.expert.business.LiteWorkingSpaceDto;
import com.simia.share.common.model.dto.expert.service.LitePackageDto;
import com.simia.share.common.model.dto.expert.service.LiteServicePriceDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class FullAnalyticDto {

    private Map<String, FullAnalyticItemDto<LitePackageDto>> packages = new HashMap<>();

    private Map<String, FullAnalyticItemDto<LiteServicePriceDto>> services = new HashMap<>();

    private Map<String, FullAnalyticItemDto<LiteWorkingSpaceDto>> workingSpaces = new HashMap<>();

    private Map<String, FullAnalyticItemDto<LiteWorkerDto>> workers = new HashMap<>();
}
