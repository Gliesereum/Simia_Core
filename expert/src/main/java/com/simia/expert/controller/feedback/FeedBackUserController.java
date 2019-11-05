package com.simia.expert.controller.feedback;

import com.simia.expert.service.feedback.FeedBackUserService;
import com.simia.share.common.model.dto.expert.feedback.FeedBackSearchDto;
import com.simia.share.common.model.dto.expert.feedback.FeedBackUserDto;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
@RestController
@RequestMapping("/feedback")
public class FeedBackUserController {

    @Autowired
    private FeedBackUserService feedBackUserService;

    @PostMapping("/params")
    public List<FeedBackUserDto> getAllBySearch(@RequestBody FeedBackSearchDto search) {
        return feedBackUserService.getAllBySearch(search);
    }

    @PostMapping("/create/record-feedback")
    public MapResponse recordFeedback(@RequestParam("recordId") UUID recordId,
                                      @RequestParam(value = "comment", required = false) String comment,
                                      @RequestParam("mark") Integer mark) {
        feedBackUserService.recordFeedback(recordId, comment, mark);
        return new MapResponse("true");
    }

    @GetMapping("/by-record")
    public FeedBackUserDto getByRecord(@RequestParam("recordId") UUID recordId) {
        return feedBackUserService.getByRecord(recordId);
    }
}    
