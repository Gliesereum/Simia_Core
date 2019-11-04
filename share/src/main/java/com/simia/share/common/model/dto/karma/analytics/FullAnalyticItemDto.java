package com.simia.share.common.model.dto.karma.analytics;

import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.karma.record.BaseRecordDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class FullAnalyticItemDto<T extends DefaultDto> {

    private T object;

    private Set<BaseRecordDto> records = new HashSet<>();
}
