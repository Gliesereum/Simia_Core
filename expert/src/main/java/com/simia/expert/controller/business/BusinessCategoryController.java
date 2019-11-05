package com.simia.expert.controller.business;

import com.simia.expert.service.business.BusinessCategoryService;
import com.simia.share.common.model.dto.expert.business.BusinessCategoryDto;
import com.simia.share.common.model.dto.expert.enumerated.BusinessType;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@RestController
@RequestMapping("/business-category")
public class BusinessCategoryController {

    @Autowired
    private BusinessCategoryService businessCategoryService;

    @GetMapping("/{id}")
    public BusinessCategoryDto getById(@PathVariable("id") UUID id) {
        return businessCategoryService.getById(id);
    }

    @GetMapping
    public List<BusinessCategoryDto> getAll() {
        return businessCategoryService.getAllSortByIndex();
    }

    @GetMapping("/by-business-type")
    public List<BusinessCategoryDto> getByBusinessType(@RequestParam("businessType") BusinessType businessType) {
        return businessCategoryService.getByBusinessType(businessType);
    }

    @GetMapping("/business-type")
    public List<BusinessType> getBusinessTypes() {
        return Arrays.asList(BusinessType.values());
    }

    @GetMapping("/by-code")
    public BusinessCategoryDto getByCode(@RequestParam("code") String code) {
        return businessCategoryService.getByCode(code);
    }

    @GetMapping("/exists/by-code")
    public MapResponse existsByCode(@RequestParam("code") String code) {
        boolean result = businessCategoryService.existByCode(code);
        return new MapResponse(result);
    }

    @PostMapping
    public BusinessCategoryDto create(@RequestBody @Valid BusinessCategoryDto workTime) {
        return businessCategoryService.create(workTime);
    }


    @PutMapping
    public BusinessCategoryDto update(@RequestBody @Valid BusinessCategoryDto workTime) {
        return businessCategoryService.update(workTime);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        businessCategoryService.delete(id);
        return new MapResponse("true");
    }
}
