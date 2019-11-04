package com.simia.share.common.model.dto.karma.business;

import com.simia.share.common.model.dto.base.geo.GeoDistanceDto;
import com.simia.share.common.model.dto.base.geo.GeoPosition;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class BusinessSearchDto {

    private UUID targetId;

    private List<UUID> serviceIds;

    private UUID businessCategoryId;

    private List<UUID> corporationIds;

    private List<UUID> businessCategoryIds;

    private GeoDistanceDto geoDistance;
    
    private List<GeoPosition> polygonPoints;

    private String fullTextQuery;

    private Boolean businessVerify;

    private List<String> tags;
    
    private Integer size;
    
    private Integer page;
}
