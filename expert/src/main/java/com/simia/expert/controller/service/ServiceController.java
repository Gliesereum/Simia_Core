package com.simia.expert.controller.service;

import com.simia.expert.service.service.ServiceService;
import com.simia.share.common.model.dto.expert.service.ServiceDto;
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
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService service;

    @GetMapping
    public List<ServiceDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ServiceDto getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @GetMapping("/by-business-category")
    public List<ServiceDto> getAllByTypeServices(@RequestParam("businessCategoryId") UUID businessCategoryId) {
        return service.getAllByBusinessCategoryId(businessCategoryId);
    }

    @PostMapping
    public ServiceDto create(@Valid @RequestBody ServiceDto dto) {
        return service.create(dto);
    }

    @PutMapping
    public ServiceDto update(@Valid @RequestBody ServiceDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new MapResponse("true");
    }
}
