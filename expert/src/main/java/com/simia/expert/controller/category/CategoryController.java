package com.simia.expert.controller.category;

import com.simia.expert.service.category.CategoryService;
import com.simia.share.common.model.dto.expert.category.CategoryDto;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public List<CategoryDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/get-all-parents")
    public List<CategoryDto> getAllParents() {
        return service.getAllParents();
    }

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public CategoryDto create(@Valid @RequestBody CategoryDto dto) {
        return service.create(dto);
    }

    @PutMapping
    public CategoryDto update(@Valid @RequestBody CategoryDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new MapResponse("true");
    }
}