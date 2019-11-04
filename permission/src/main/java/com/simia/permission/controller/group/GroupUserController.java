package com.simia.permission.controller.group;

import com.simia.permission.service.group.GroupService;
import com.simia.permission.service.group.GroupUserService;
import com.simia.share.common.model.dto.permission.enumerated.GroupPurpose;
import com.simia.share.common.model.dto.permission.group.GroupDto;
import com.simia.share.common.model.dto.permission.group.GroupUserDto;
import com.simia.share.common.model.response.MapResponse;
import com.simia.share.common.security.model.UserAuthentication;
import com.simia.share.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@RestController
@RequestMapping("/group-user")
public class GroupUserController {
	
	@Autowired
	private GroupUserService groupUserService;
	
	@Autowired
	private GroupService groupService;
	
	@PostMapping("/by-group-purpose")
	public List<GroupUserDto> addUserByGroupPurpose(@RequestParam("groupPurpose") GroupPurpose groupPurpose,
	                                                @RequestParam("userId") UUID userId) {
		return groupUserService.addToGroupByPurpose(groupPurpose, userId);
	}
	
	@PostMapping
	public GroupUserDto addUserToGroup(@Valid @RequestBody GroupUserDto groupUser) {
		return groupUserService.addToGroup(groupUser);
	}
	
	@DeleteMapping
	public MapResponse removeFromGroup(@RequestParam("groupId") UUID groupId, @RequestParam("userId") UUID userId) {
		groupUserService.removeFromGroup(groupId, userId);
		return new MapResponse("success");
	}
	
	@GetMapping("/my-group")
	public List<GroupDto> getGroupForCurrentUser() {
		List<GroupDto> result;
		UserAuthentication authentication = SecurityUtil.getUser();
		result = groupUserService.getGroupByUser(authentication.getUser(), SecurityUtil.getApplicationId());
		return result;
	}
	
	@GetMapping("/group-by-phone")
    public List<GroupDto> getGroupByPhone(@RequestParam("phone") String phone) {
	    return groupUserService.getByPhone(phone, SecurityUtil.getApplicationId());
    }
	
	@GetMapping("/user-have-group")
	public MapResponse userHaveGroup(@RequestParam("groupPurpose") GroupPurpose groupPurpose) {
		return new MapResponse(groupUserService.groupExistInUser(groupPurpose));
	}
	
	@GetMapping("/by-group-purpose")
	public List<GroupUserDto> getByGroupPurpose(@RequestParam("groupPurpose") GroupPurpose groupPurpose) {
		return groupUserService.getByGroupPurpose(groupPurpose);
	}
}
