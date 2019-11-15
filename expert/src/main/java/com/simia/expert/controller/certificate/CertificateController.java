package com.simia.expert.controller.certificate;

import com.simia.expert.service.certificate.CertificateService;
import com.simia.share.common.model.dto.expert.certificate.CertificateDto;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    @Autowired
    private CertificateService service;

    @GetMapping("/{id}")
    public CertificateDto getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public CertificateDto create(@Valid @RequestBody CertificateDto dto) {
        return service.create(dto);
    }

    @PutMapping
    public CertificateDto update(@Valid @RequestBody CertificateDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new MapResponse("true");
    }

}