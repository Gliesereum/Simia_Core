package com.simia.account.controller.user;

import com.simia.account.service.user.UserPhoneService;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.account.user.UserPhoneDto;
import com.simia.share.common.model.response.MapResponse;
import com.simia.share.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.simia.share.common.exception.messages.PhoneExceptionMessage.PHONE_CODE_EMPTY;
import static com.simia.share.common.exception.messages.PhoneExceptionMessage.PHONE_EMPTY;

/**
 * @author vitalij
 */
@RestController
@RequestMapping("/phone")
public class UserPhoneController {

    @Autowired
    private UserPhoneService phoneService;

    @PostMapping
    public UserPhoneDto create(@RequestBody Map<String, String> params) {
        String phone = params.get("phone");
        String code = params.get("code");
        if (StringUtils.isEmpty(phone)) {
            throw new ClientException(PHONE_EMPTY);
        }
        if (StringUtils.isEmpty(code)) {
            throw new ClientException(PHONE_CODE_EMPTY);
        }
        return phoneService.create(phone, code);
    }

    @PutMapping
    public UserPhoneDto update(@RequestBody Map<String, String> params) {
        String phone = params.get("phone");
        String code = params.get("code");
        if (StringUtils.isEmpty(phone)) {
            throw new ClientException(PHONE_EMPTY);
        }
        if (StringUtils.isEmpty(code)) {
            throw new ClientException(PHONE_CODE_EMPTY);
        }
        return phoneService.update(phone, code);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        phoneService.delete(id);
        return new MapResponse("true");
    }

    @GetMapping("/by-user")
    public UserPhoneDto getByUserId() {
        return phoneService.getByUserId(SecurityUtil.getUserId());
    }

    @GetMapping("/code")
    public MapResponse sendCode(@RequestParam(value = "phone") String phone,
                                @RequestParam(value = "dev", defaultValue = "false", required = false) Boolean devMode) {
        phoneService.sendCode(phone, devMode);
        return new MapResponse("sent", "true");
    }

    @GetMapping("/by-user-ids")
    public List<UserPhoneDto> getByUserIds(@RequestParam("ids") List<UUID> ids) {
        return phoneService.getByUserIds(ids);
    }
}
