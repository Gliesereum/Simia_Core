package com.simia.expert.controller.promo;

import com.simia.expert.service.speacker.SpeakerService;
import com.simia.share.common.model.dto.expert.speacker.SpeakerDto;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/promotion/speaker")
public class SpeakerController {

    @Autowired
    private SpeakerService service;

    @GetMapping("/{id}")
    public SpeakerDto getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public SpeakerDto create(@Valid @RequestBody SpeakerDto dto) {
        return service.create(dto);
    }

    @PutMapping
    public SpeakerDto update(@Valid @RequestBody SpeakerDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new MapResponse("true");
    }
}