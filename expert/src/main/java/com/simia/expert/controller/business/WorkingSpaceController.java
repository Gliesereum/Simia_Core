package com.simia.expert.controller.business;

import com.simia.expert.service.business.WorkingSpaceService;
import com.simia.share.common.model.dto.expert.business.WorkingSpaceDto;
import com.simia.share.common.model.dto.expert.business.WorkingSpaceServicePriceDto;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@RestController
@RequestMapping("/working-space")
public class WorkingSpaceController {

    @Autowired
    private WorkingSpaceService workingSpaceService;

    @GetMapping("/{businessId}")
    public List<WorkingSpaceDto> getAll(@PathVariable("businessId") UUID businessId) {
        return workingSpaceService.getByBusinessId(businessId, true);
    }

    @PostMapping
    public WorkingSpaceDto create(@RequestBody @Valid WorkingSpaceDto space) {
        return workingSpaceService.create(space);
    }

    @PostMapping("/list")
    public List<WorkingSpaceDto> createList(@RequestBody List<WorkingSpaceDto> spaces) {
        return workingSpaceService.create(spaces);
    }

    @PutMapping
    public WorkingSpaceDto update(@RequestBody @Valid WorkingSpaceDto space) {
        return workingSpaceService.update(space);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        workingSpaceService.delete(id);
        return new MapResponse("true");
    }

    @PostMapping("/add-service-price")
    public List<WorkingSpaceServicePriceDto> addServicePrice(@RequestBody List<WorkingSpaceServicePriceDto> dtos) {
        return workingSpaceService.addServicePrice(dtos);
    }
}
