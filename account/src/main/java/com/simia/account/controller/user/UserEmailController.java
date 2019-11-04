package com.simia.account.controller.user;

import com.simia.account.service.user.UserEmailService;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.account.user.UserEmailDto;
import com.simia.share.common.model.response.MapResponse;
import com.simia.share.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

import static com.simia.share.common.exception.messages.EmailExceptionMessage.EMAIL_CODE_EMPTY;
import static com.simia.share.common.exception.messages.EmailExceptionMessage.EMAIL_EMPTY;

/**
 * @author vitalij
 */
@RestController
@RequestMapping("/email")
public class UserEmailController {

    @Autowired
    private UserEmailService emailService;

    @PostMapping
    public UserEmailDto create(@RequestBody Map<String, String> params) {
        String email = params.get("email");
        String code = params.get("code");
        if (StringUtils.isEmpty(email)) {
            throw new ClientException(EMAIL_EMPTY);
        }
        if (StringUtils.isEmpty(code)) {
            throw new ClientException(EMAIL_CODE_EMPTY);
        }
        return emailService.create(email, code);
    }

    @PutMapping
    public UserEmailDto update(@RequestBody Map<String, String> params) {
        String email = params.get("email");
        String code = params.get("code");
        if (StringUtils.isEmpty(email)) {
            throw new ClientException(EMAIL_EMPTY);
        }
        if (StringUtils.isEmpty(code)) {
            throw new ClientException(EMAIL_CODE_EMPTY);
        }
        return emailService.update(email, code);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        emailService.delete(id);
        return new MapResponse("true");
    }

    @GetMapping("/by-user")
    public UserEmailDto getByUserId() {
        return emailService.getByUserId(SecurityUtil.getUserId());
    }

    @GetMapping("/code")
    public MapResponse sendCode(@RequestParam(name = "email") String email,
                                @RequestParam(name = "dev", defaultValue = "false", required = false) Boolean devMode) {
        emailService.sendCode(email, devMode);
        return new MapResponse("sent", "true");
    }
}
