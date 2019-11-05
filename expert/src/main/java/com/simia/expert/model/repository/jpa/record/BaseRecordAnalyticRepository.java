package com.simia.expert.model.repository.jpa.record;

import com.simia.expert.model.entity.record.BaseRecordEntity;
import com.simia.share.common.model.dto.expert.analytics.AnalyticFilterDto;

import java.util.List;

/**
 * @author vitalij
 * @version 1.0
 */
public interface BaseRecordAnalyticRepository {

    List<BaseRecordEntity> getRecordsByFilter(AnalyticFilterDto filter);
}
