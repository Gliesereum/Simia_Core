package com.simia.expert.controller.content;

import com.simia.expert.service.content.PackageService;
import com.simia.share.common.model.dto.expert.content.PackageDto;
import com.simia.share.common.model.dto.expert.content.PackageFullDto;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/package")
public class PackageController {

    @Autowired
    private PackageService service;

    @GetMapping
    public List<PackageDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PackageDto getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public PackageDto create(@Valid @RequestBody PackageDto dto) {
        return service.create(dto);
    }

    @PutMapping
    public PackageDto update(@Valid @RequestBody PackageDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new MapResponse("true");
    }

}