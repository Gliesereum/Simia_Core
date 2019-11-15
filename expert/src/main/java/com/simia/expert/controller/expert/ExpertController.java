package com.simia.expert.controller.expert;

import com.simia.expert.service.expert.ExpertService;
import com.simia.share.common.model.dto.expert.expert.ExpertDto;
import com.simia.share.common.model.response.MapResponse;
import com.simia.share.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/expert")
public class ExpertController {
	
	@Autowired
	private ExpertService service;
	
	@GetMapping
	public List<ExpertDto> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public ExpertDto getById(@PathVariable("id") UUID id) {
		return service.getById(id);
	}
	
	@GetMapping("/me")
	public ExpertDto getMe() {
		SecurityUtil.checkUserByBanStatus();
		return service.getByUserId(SecurityUtil.getUserId());
	}
	
	@DeleteMapping("/me")
	public MapResponse delete() {
		service.deleteByUserId(SecurityUtil.getUserId());
		return new MapResponse("true");
	}
}
