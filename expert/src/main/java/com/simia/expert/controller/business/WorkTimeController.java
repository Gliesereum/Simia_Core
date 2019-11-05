package com.simia.expert.controller.business;

import com.simia.expert.service.business.WorkTimeService;
import com.simia.share.common.model.dto.expert.business.WorkTimeDto;
import com.simia.share.common.model.dto.expert.enumerated.WorkTimeType;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@RestController
@RequestMapping("/work-time")
public class WorkTimeController {

    @Autowired
    private WorkTimeService workTimeService;

    @GetMapping("/{businessId}")
    public List<WorkTimeDto> getAll(@PathVariable("businessId") UUID businessId) {
        return workTimeService.getByObjectId(businessId);
    }

    @PostMapping
    public WorkTimeDto create(@RequestBody @Valid WorkTimeDto workTime) {
        return workTimeService.create(workTime);
    }

    @PostMapping("/list")
    public List<WorkTimeDto> createList(@RequestBody @Valid List<WorkTimeDto> workTimes) {
        return workTimeService.create(workTimes);
    }

    @PutMapping("/list")
    public List<WorkTimeDto> updateList(@RequestBody @Valid List<WorkTimeDto> workTimes) {
        return workTimeService.update(workTimes);
    }

    @PutMapping
    public WorkTimeDto update(@RequestBody @Valid WorkTimeDto workTime) {
        return workTimeService.update(workTime);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id, @RequestParam("businessCategoryId") UUID businessCategoryId, @RequestParam("type")WorkTimeType type) {
        workTimeService.delete(id, businessCategoryId, type);
        return new MapResponse("true");
    }
}
