package com.simia.expert.controller.promo;

import com.simia.expert.service.promo.GiveAwayService;
import com.simia.share.common.model.dto.expert.promo.GiveAwayDto;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/give-away")
public class GiveAwayController {

    @Autowired
    private GiveAwayService service;

    @GetMapping("/{id}")
    public GiveAwayDto getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public GiveAwayDto create(@Valid @RequestBody GiveAwayDto dto) {
        return service.create(dto);
    }

    @PutMapping
    public GiveAwayDto update(@Valid @RequestBody GiveAwayDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new MapResponse("true");
    }

}

