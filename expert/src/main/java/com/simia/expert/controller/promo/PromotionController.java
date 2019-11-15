package com.simia.expert.controller.promo;

import com.simia.expert.service.promo.PromotionService;
import com.simia.share.common.model.dto.account.user.PublicUserDto;
import com.simia.share.common.model.dto.account.user.UserDto;
import com.simia.share.common.model.dto.expert.promo.PromotionDto;
import com.simia.share.common.model.dto.expert.promo.PromotionDto;
import com.simia.share.common.model.dto.expert.promo.PromotionFullDto;
import com.simia.share.common.model.dto.expert.tag.TagDto;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/promo")
public class PromotionController {

    @Autowired
    private PromotionService service;

    @GetMapping
    public List<PromotionDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PromotionDto getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public PromotionDto create(@Valid @RequestBody PromotionDto dto) {
        return service.create(dto);
    }

    @PutMapping
    public PromotionDto update(@Valid @RequestBody PromotionDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new MapResponse("true");
    }
}