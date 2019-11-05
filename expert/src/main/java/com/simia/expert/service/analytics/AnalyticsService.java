package com.simia.expert.service.analytics;

import com.simia.share.common.model.dto.expert.analytics.AnalyticDto;
import com.simia.share.common.model.dto.expert.analytics.AnalyticFilterDto;
import com.simia.share.common.model.dto.expert.analytics.CountAnalyticDto;

/**
 * @author vitalij
 * @version 1.0
 */
public interface AnalyticsService {

    AnalyticDto getAnalyticByFilter(AnalyticFilterDto filter);

    CountAnalyticDto getCountAnalyticByFilter(AnalyticFilterDto filter, boolean includeRecord);
}
