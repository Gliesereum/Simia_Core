package com.simia.expert.controller.karma;

import com.simia.expert.service.karma.KarmaService;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.expert.karma.KarmaDto;
import com.simia.share.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.simia.share.common.exception.messages.CommonExceptionMessage.USER_IS_ANONYMOUS;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@RestController
@RequestMapping("/karma")
public class KarmaController {

    @Autowired
    private KarmaService karmaService;

    @GetMapping("/me")
    public KarmaDto userKarma() {
        UUID userId = SecurityUtil.getUserId();
        if (userId == null) {
            throw new ClientException(USER_IS_ANONYMOUS);
        }
        return karmaService.getByUserId(userId);
    }
}
