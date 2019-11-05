package com.simia.share.common.model.dto.expert.analytics;

import com.simia.share.common.model.dto.expert.record.BaseRecordDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author vitalij
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class AnalyticDto {

    private Map<String, Set<BaseRecordDto>> packages = new HashMap<>();

    private Map<String, Set<BaseRecordDto>> services = new HashMap<>();

    private Map<String, Set<BaseRecordDto>> workingSpaces = new HashMap<>();

    private Map<String, Set<BaseRecordDto>> workers = new HashMap<>();
}
