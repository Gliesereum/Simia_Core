package com.simia.expert.controller.analytics;

import com.simia.expert.service.analytics.AnalyticsService;
import com.simia.share.common.model.dto.expert.analytics.AnalyticDto;
import com.simia.share.common.model.dto.expert.analytics.AnalyticFilterDto;
import com.simia.share.common.model.dto.expert.analytics.CountAnalyticDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author vitalij
 * @version 1.0
 */
@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService service;

    @PostMapping("/by-filter")
    public AnalyticDto getAnalyticByFilter(@RequestBody AnalyticFilterDto filter) {
        return service.getAnalyticByFilter(filter);
    }

    @PostMapping("/count/by-filter")
    public CountAnalyticDto getCountByFilter(@RequestBody AnalyticFilterDto filter,
                                             @RequestParam(value = "includeRecord", required = false, defaultValue = "false") boolean includeRecord) {
        return service.getCountAnalyticByFilter(filter, includeRecord);
    }
}
