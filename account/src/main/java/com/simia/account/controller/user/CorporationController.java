package com.simia.account.controller.user;

import com.simia.account.service.user.CorporationEmployeeService;
import com.simia.account.service.user.CorporationService;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.account.user.CorporationDto;
import com.simia.share.common.model.dto.account.user.CorporationEmployeeDto;
import com.simia.share.common.model.dto.account.user.CorporationSharedOwnershipDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.model.response.MapResponse;
import com.simia.share.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.CommonExceptionMessage.USER_IS_ANONYMOUS;

/**
 * @author vitalij
 */
@RestController
@RequestMapping("/corporation")
public class CorporationController {

    @Autowired
    private CorporationService service;

    @Autowired
    private CorporationEmployeeService employeeService;

    @GetMapping
    public Page<CorporationDto> getAll(@PageableDefault(page = 0, size = 100, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAll(ObjectState.ACTIVE, pageable);
    }

    @GetMapping("/{id}")
    public CorporationDto getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public CorporationDto create(@RequestBody CorporationDto dto) {
        return service.create(dto);
    }

    @PutMapping
    public CorporationDto update(@RequestBody CorporationDto dto) {
        return service.update(dto);
    }

    @GetMapping("/by-user")
    public List<CorporationDto> byUser() {
        if (SecurityUtil.isAnonymous()) {
            throw new ClientException(USER_IS_ANONYMOUS);
        }
        return service.getByUserId(SecurityUtil.getUserId());
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new MapResponse("true");
    }

    @PostMapping("/owner")
    public MapResponse addOwnerCorporation(@Valid @RequestBody CorporationSharedOwnershipDto dto) {
        service.addOwnerCorporation(dto);
        return new MapResponse("true");
    }

    @DeleteMapping("/owner/{id}")
    public MapResponse removeOwnerCorporation(@PathVariable("id") UUID id) {
        service.removeOwnerCorporation(id);
        return new MapResponse("true");
    }

    @GetMapping("/employee/{corporationId}")
    public List<CorporationEmployeeDto> employeeGetAll(@PathVariable("corporationId") UUID corporationId) {
        return employeeService.getAllByCorporationId(corporationId);
    }

    @GetMapping("/employee/{id}")
    public CorporationEmployeeDto employeeGetById(@PathVariable("id") UUID id) {
        return employeeService.getById(id);
    }

    @PostMapping("/employee")
    public CorporationEmployeeDto employeeCreate(@RequestBody CorporationEmployeeDto dto) {
        return employeeService.create(dto);
    }

    @PutMapping("/employee")
    public CorporationEmployeeDto employeeUpdate(@RequestBody CorporationEmployeeDto dto) {
        return employeeService.update(dto);
    }

    @DeleteMapping("/employee/{id}")
    public MapResponse employeeDelete(@PathVariable("id") UUID id) {
        employeeService.delete(id);
        return new MapResponse("true");
    }
}
