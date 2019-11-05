package com.simia.expert.controller.chat;

import com.simia.expert.service.chat.ChatService;
import com.simia.share.common.model.dto.expert.chat.ChatDto;
import com.simia.share.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService service;

    @GetMapping("/current-user")
    public List<ChatDto> getAllByCurrentUser() {
        return service.getAllByUserId(SecurityUtil.getUserId());
    }

    @GetMapping("/current-user/by-business-id/{id}")
    public ChatDto getByUserIdAndBusinessId(@PathVariable("id") UUID businessId) {
        return service.getByUserIdAndBusinessId(SecurityUtil.getUserId(), businessId);
    }

    @GetMapping("/by-business-id/{id}")
    public List<ChatDto> getAllByBusinessId(@PathVariable("id") UUID businessId) {
        return service.getAllByBusinessId(businessId);
    }

}    
