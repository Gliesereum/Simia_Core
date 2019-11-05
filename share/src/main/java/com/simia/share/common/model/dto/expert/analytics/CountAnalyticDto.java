package com.simia.share.common.model.dto.expert.analytics;

import com.simia.share.common.model.dto.expert.business.LiteWorkerDto;
import com.simia.share.common.model.dto.expert.business.LiteWorkingSpaceDto;
import com.simia.share.common.model.dto.expert.service.LitePackageDto;
import com.simia.share.common.model.dto.expert.service.LiteServicePriceDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class CountAnalyticDto {

    private List<CountAnalyticItemDto<LitePackageDto>> packages = new ArrayList<>();

    private List<CountAnalyticItemDto<LiteServicePriceDto>> services = new ArrayList<>();

    private List<CountAnalyticItemDto<LiteWorkingSpaceDto>> workingSpaces = new ArrayList<>();

    private List<CountAnalyticItemDto<LiteWorkerDto>> workers = new ArrayList<>();
}
