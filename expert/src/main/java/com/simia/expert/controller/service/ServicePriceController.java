package com.simia.expert.controller.service;

import com.simia.expert.service.service.ServiceClassPriceService;
import com.simia.expert.service.service.ServicePriceService;
import com.simia.share.common.model.dto.expert.service.ServiceClassPriceDto;
import com.simia.share.common.model.dto.expert.service.ServicePriceDto;
import com.simia.share.common.model.dto.expert.tag.TagDto;
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
@RequestMapping("/price")
public class ServicePriceController {

    @Autowired
    private ServicePriceService servicePriceService;

    @Autowired
    private ServiceClassPriceService serviceClassPriceService;

    @GetMapping
    public List<ServicePriceDto> getAll() {
        return servicePriceService.getAll();
    }

    @GetMapping("/package/{id}")
    public List<ServicePriceDto> getAllByPackage(@PathVariable("id") UUID id) {
        return servicePriceService.getAllByPackage(id);
    }

    @GetMapping("/{id}")
    public ServicePriceDto getById(@PathVariable("id") UUID id) {
        return servicePriceService.getById(id);
    }

    @PostMapping
    public ServicePriceDto create(@Valid @RequestBody ServicePriceDto dto) {
        return servicePriceService.create(dto);
    }

    @PutMapping
    public ServicePriceDto update(@Valid @RequestBody ServicePriceDto dto) {
        return servicePriceService.update(dto);
    }

    @GetMapping("/by-business/{id}")
    public List<ServicePriceDto> getByBusinessId(@PathVariable("id") UUID id) {
        return servicePriceService.getByBusinessId(id);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        servicePriceService.delete(id);
        return new MapResponse("true");
    }

    @PostMapping("/class")
    public ServicePriceDto createClass(@Valid @RequestBody ServiceClassPriceDto dto) {
        return serviceClassPriceService.createAndGetPrice(dto);
    }

    @PutMapping("/class")
    public ServicePriceDto updateClass(@Valid @RequestBody ServiceClassPriceDto dto) {
        return serviceClassPriceService.updateAndGetPrice(dto);
    }

    @DeleteMapping("/class/{priceId}/{classId}")
    public MapResponse deleteClass(@PathVariable("priceId") UUID priceId, @PathVariable("classId") UUID classId) {
        serviceClassPriceService.delete(priceId, classId);
        return new MapResponse("true");
    }

    @GetMapping("/{priceId}/class")
    public List<ServiceClassPriceDto> getByPriceId(@PathVariable("priceId") UUID priceId) {
        return serviceClassPriceService.getByPriceId(priceId);
    }

    @PostMapping("/classes/{idPrice}")
    public ServicePriceDto addClasses(@PathVariable("idPrice") UUID idPrice, @RequestBody(required = false) List<UUID> classes) {
        return serviceClassPriceService.createAndGetPrice(idPrice, classes);
    }

    @PostMapping("/filter-attribute/{idPrice}/{idAttribute}")
    public MapResponse addFilterAttribute(@PathVariable("idPrice") UUID idPrice, @PathVariable("idAttribute") UUID idAttribute) {
        servicePriceService.addFilterAttribute(idPrice, idAttribute);
        return new MapResponse("true");
    }

    @PostMapping("/filter-attributes/{idPrice}")
    public ServicePriceDto addFilterAttributes(@PathVariable("idPrice") UUID idPrice, @RequestBody(required = false) List<UUID> idAttributes) {
        return servicePriceService.addFilterAttributes(idPrice, idAttributes);
    }

    @DeleteMapping("/remove/filter-attribute/{idPrice}/{idAttribute}")
    public MapResponse removeFilterAttribute(@PathVariable("idPrice") UUID idPrice, @PathVariable("idAttribute") UUID idAttribute) {
        servicePriceService.removeFilterAttribute(idPrice, idAttribute);
        return new MapResponse("true");
    }

    @PostMapping("/tag/add")
    public List<TagDto> addTag(@RequestParam(value = "tagId") UUID tagId,
                               @RequestParam(value = "idPrice") UUID idPrice) {
        return servicePriceService.addTag(tagId, idPrice);
    }

    @GetMapping("/{id}/tags")
    public List<TagDto> getTags(@PathVariable("id") UUID idPrice) {
        return servicePriceService.getTags(idPrice);
    }

    @PostMapping("/tag/save")
    public List<TagDto> saveTag(@RequestParam(value = "tagId") List<UUID> tagId,
                                @RequestParam(value = "idPrice") UUID idPrice) {
        return servicePriceService.saveTags(tagId, idPrice);
    }

    @PostMapping("/tag/remove")
    public List<TagDto> removeTag(@RequestParam(value = "tagId") UUID tagId,
                                  @RequestParam(value = "idPrice") UUID idPrice) {
        return servicePriceService.removeTag(tagId, idPrice);
    }

}
