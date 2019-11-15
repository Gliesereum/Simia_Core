package com.simia.expert.controller.content;

import com.simia.expert.service.content.ContentService;
import com.simia.share.common.model.dto.expert.content.ContentDto;
import com.simia.share.common.model.dto.expert.content.ContentFullDto;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService service;

    @GetMapping
    public List<ContentDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ContentDto getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public ContentDto create(@Valid @RequestBody ContentDto dto) {
        return service.create(dto);
    }

    @PutMapping
    public ContentDto update(@Valid @RequestBody ContentDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new MapResponse("true");
    }

}