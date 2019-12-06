package com.simia.expert.controller.subscriber;

import com.simia.expert.service.subscriber.SubscriberService;
import com.simia.share.common.model.dto.expert.subscriber.SubscriberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {

    @Autowired
    private SubscriberService service;

    @GetMapping("/my-subscriptions")
    public List<SubscriberDto> mySubscriptions() {
        return service.mySubscriptions();
    }

    @PostMapping("/subscribe-to-content")
    public SubscriberDto subscribeToContent(@RequestParam("contentId") UUID contentId) {
        return service.subscribeToContent(contentId);
    }
}