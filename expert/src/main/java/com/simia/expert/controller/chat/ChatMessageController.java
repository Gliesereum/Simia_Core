package com.simia.expert.controller.chat;

import com.simia.expert.service.chat.ChatMessageService;
import com.simia.share.common.model.dto.expert.chat.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@RestController
@RequestMapping("/chat-message")
public class ChatMessageController {

    @Autowired
    private ChatMessageService service;

    @GetMapping("/by-chat/{id}")
    public List<ChatMessageDto> getAllByChatId(@PathVariable("id") UUID id) {
        return service.getAllByChatId(id);
    }

    @PostMapping("/by-business/{id}")
    public ChatMessageDto createByBusinessId(@PathVariable("id") UUID id, @RequestBody String message) {
        return service.createByBusinessId(id, message);
    }

    @PostMapping("/by-chat/{id}")
    public ChatMessageDto createByChatId(@PathVariable("id") UUID id, @RequestBody String message) {
        return service.createByChatId(id, message);
    }

}    
