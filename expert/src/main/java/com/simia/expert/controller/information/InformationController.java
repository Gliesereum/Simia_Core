package com.simia.expert.controller.information;

import com.simia.expert.service.information.InformationService;
import com.simia.share.common.model.dto.expert.information.InformationDto;
import com.simia.share.common.model.response.MapResponse;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @GetMapping("/{id}")
    public InformationDto getById(@PathVariable("id") UUID id) {
        return informationService.getById(id);
    }

    @PostMapping
    public InformationDto create(@Valid @RequestBody InformationDto dto) {
        return informationService.create(dto);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        informationService.delete(id);
        return new MapResponse("true");
    }
}
