package com.simia.expert.controller.agent;

import com.simia.expert.service.audit.AgentService;
import com.simia.share.common.model.dto.expert.agent.AgentDto;
import com.simia.share.common.model.response.MapResponse;
import com.simia.share.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @GetMapping("/current-user-agent")
    public MapResponse currentUserIsAgent() {
        SecurityUtil.checkUserByBanStatus();
        return new MapResponse(agentService.existByUserIdAndActive(SecurityUtil.getUserId()));
    }

    @PostMapping("/request")
    public AgentDto request() {
        SecurityUtil.checkUserByBanStatus();
        return agentService.createRequest(SecurityUtil.getUserId());
    }
}
