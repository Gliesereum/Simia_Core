package com.simia.expert.controller.followers;

import com.simia.expert.service.followers.FollowerService;
import com.simia.share.common.model.dto.expert.followers.FollowerDto;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/followers")
public class FollowerController {

    @Autowired
    private FollowerService service;

    @GetMapping("/by-expert")
    public List<FollowerDto> getAllByExpertId(@RequestParam("id") UUID id) {
        return service.getAllByExpertId(id);
    }

    @GetMapping("/i-followed-to-experts")
    public List<FollowerDto> iFollowedToExperts() {
        return service.iFollowedToExperts();
    }
}