package com.simia.expert.controller.administrator;

import com.simia.expert.facade.business.BusinessPermissionFacade;
import com.simia.expert.model.common.BusinessPermission;
import com.simia.expert.service.administrator.BusinessAdministratorService;
import com.simia.share.common.model.dto.expert.administrator.DetailedBusinessAdministratorDto;
import com.simia.share.common.model.response.MapResponse;
import com.simia.share.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@RestController
@RequestMapping("/business-administrator")
public class BusinessAdministratorController {

    @Autowired
    private BusinessAdministratorService businessAdministratorService;

    @Autowired
    private BusinessPermissionFacade businessPermissionFacade;

    @PostMapping
    public DetailedBusinessAdministratorDto create(@RequestParam("businessId") UUID businessId,
                                                    @RequestParam("userId") UUID userId) {
        return businessAdministratorService.create(userId, businessId);
    }

    @DeleteMapping
    public MapResponse delete(@RequestParam("businessId") UUID businessId,
                               @RequestParam("userId") UUID userId) {
        businessAdministratorService.delete(userId, businessId);
        return MapResponse.resultTrue();
    }

    @GetMapping("/by-corporation")
    public List<DetailedBusinessAdministratorDto> getByCorporation(@RequestParam("id") UUID id) {
        return businessAdministratorService.getByCorporationId(id);
    }

    @GetMapping("/by-business")
    public List<DetailedBusinessAdministratorDto> getByBusiness(@RequestParam("id") UUID id) {
        return businessAdministratorService.getByBusinessId(id);
    }

    @GetMapping("/for-business")
    public MapResponse checkCurrentUserAdmin(@RequestParam("businessId") UUID businessId) {
        SecurityUtil.checkUserByBanStatus();
        return new MapResponse(businessPermissionFacade.isHavePermissionByBusiness(businessId, BusinessPermission.VIEW_PHONE));
    }
}
