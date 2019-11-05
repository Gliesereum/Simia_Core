package com.simia.expert.controller.business;

import com.simia.expert.facade.business.BusinessPermissionFacade;
import com.simia.expert.facade.business.BusinessSearchFacade;
import com.simia.expert.facade.client.ClientFacade;
import com.simia.expert.facade.client.ClientSearchFacade;
import com.simia.expert.model.common.BusinessPermission;
import com.simia.expert.model.document.BusinessDocument;
import com.simia.expert.model.document.ClientDocument;
import com.simia.expert.service.business.BaseBusinessService;
import com.simia.expert.service.comment.CommentService;
import com.simia.expert.service.es.BusinessEsService;
import com.simia.expert.service.media.MediaService;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.expert.business.*;
import com.simia.share.common.model.dto.expert.business.group.BusinessGroupDto;
import com.simia.share.common.model.dto.expert.business.search.BusinessGroupSearchDto;
import com.simia.share.common.model.dto.expert.comment.CommentDto;
import com.simia.share.common.model.dto.expert.comment.CommentFullDto;
import com.simia.share.common.model.dto.expert.comment.RatingDto;
import com.simia.share.common.model.dto.expert.media.MediaDto;
import com.simia.share.common.model.dto.expert.media.MediaListUpdateDto;
import com.simia.share.common.model.dto.expert.tag.TagDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.model.response.MapResponse;
import com.simia.share.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.KarmaExceptionMessage.ANONYMOUS_CANT_COMMENT;
import static com.simia.share.common.exception.messages.KarmaExceptionMessage.BUSINESS_NOT_FOUND;

/**
 * @author vitalij
 * @version 1.0
 */
@RestController
@RequestMapping("/business")
public class BaseBusinessController {

    @Autowired
    private BaseBusinessService baseBusinessService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private BusinessEsService businessEsService;

    @Autowired
    private ClientFacade clientFacade;
    
    @Autowired
    private ClientSearchFacade clientSearchFacade;

    @Autowired
    private BusinessPermissionFacade businessPermissionFacade;
    
    @Autowired
    private BusinessSearchFacade businessSearchFacade;

    //++++++++++++++ Base +++++++++++++++++++++++

    @GetMapping("/{id}")
    public BaseBusinessDto getById(@PathVariable("id") UUID id) {
        return baseBusinessService.getById(id);
    }

    @GetMapping
    public Page<LiteBusinessDto> getAll(@PageableDefault(page = 0, size = 100, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return baseBusinessService.getAllLite(ObjectState.ACTIVE, pageable);
    }

    @PostMapping
    public BaseBusinessDto create(@RequestBody @Valid BaseBusinessDto business) {
        return baseBusinessService.create(business);
    }

    @PutMapping
    public BaseBusinessDto update(@RequestBody @Valid BaseBusinessDto business) {
        return baseBusinessService.update(business);
    }

    @PutMapping("/move-geo-point")
    public BaseBusinessDto moveGeoPoint(@RequestParam("businessId") UUID businessId,
                                        @RequestParam("address") String address,
                                        @RequestParam("latitude") Double latitude,
                                        @RequestParam("longitude") Double longitude,
                                        @RequestParam(value = "timeZone", required = false) Integer timeZone) {
        return baseBusinessService.moveGeoPoint(businessId, address, latitude, longitude, timeZone);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        baseBusinessService.delete(id);
        return new MapResponse("true");
    }

    @DeleteMapping("/by-corporation-id/{id}")
    public MapResponse deleteByCorporationId(@PathVariable("id") UUID id) {
        baseBusinessService.deleteByCorporationIdCheckPermission(id);
        return new MapResponse("true");
    }

    @GetMapping("/is-owner")
    public MapResponse checkCurrentUserOwner(@RequestParam("businessId") UUID businessId) {
        SecurityUtil.checkUserByBanStatus();
        return new MapResponse(businessPermissionFacade.isHavePermissionByBusiness(businessId, BusinessPermission.BUSINESS_ADMINISTRATION));
    }

    //++++++++++++++ Empty Business +++++++++++++++++++++++

    @PostMapping("/create-empty")
    public BaseBusinessDto createEmptyBusiness(@RequestBody @Valid EmptyBusinessDto business) {
        return baseBusinessService.createEmptyBusiness(business);
    }

    //++++++++++++++ Media +++++++++++++++++++++++

    @GetMapping("/{id}/media")
    public List<MediaDto> getMediaByBusiness(@PathVariable("id") UUID id) {
        return mediaService.getByObjectId(id);
    }

    @PostMapping("/media")
    public MediaDto create(@RequestBody @Valid MediaDto media) {
        businessPermissionFacade.checkPermissionByBusiness(media.getObjectId(), BusinessPermission.BUSINESS_ADMINISTRATION);
        return mediaService.create(media);
    }
    
    @PostMapping("/media/list")
    public List<MediaDto> updateList(@RequestBody @Valid MediaListUpdateDto medias) {
        businessPermissionFacade.checkPermissionByBusiness(medias.getObjectId(), BusinessPermission.BUSINESS_ADMINISTRATION);
        return mediaService.updateList(medias);
    }

    @PutMapping("/media")
    public MediaDto update(@RequestBody @Valid MediaDto media) {
        businessPermissionFacade.checkPermissionByBusiness(media.getObjectId(), BusinessPermission.BUSINESS_ADMINISTRATION);
        return mediaService.update(media);
    }

    @DeleteMapping("/{id}/media/{mediaId}")
    public MapResponse delete(@PathVariable("id") UUID businessId, @PathVariable("mediaId") UUID mediaId) {
        businessPermissionFacade.checkPermissionByBusiness(businessId, BusinessPermission.BUSINESS_ADMINISTRATION);
        mediaService.delete(mediaId, businessId);
        return new MapResponse("true");
    }

    //++++++++++++++ comment +++++++++++++++++++++++

    @GetMapping("/{id}/rating")
    public RatingDto getRating(@PathVariable("id") UUID id) {
        return commentService.getRating(id);
    }

    @GetMapping("/{id}/comment")
    public List<CommentFullDto> getCommentByBusiness(@PathVariable("id") UUID id) {
        return commentService.findFullByObjectId(id);
    }

    @GetMapping("/{id}/comment/current-user")
    public CommentFullDto getCommentByBusinessForUser(@PathVariable("id") UUID id) {
        return commentService.findByObjectIdForCurrentUser(id);
    }

    @PostMapping("/{id}/comment")
    public CommentDto addComment(@PathVariable("id") UUID id,
                                 @RequestBody @Valid CommentDto comment) {
        UUID userId = SecurityUtil.getUserId();
        if (userId == null) {
            throw new ClientException(ANONYMOUS_CANT_COMMENT);
        }
        if (!baseBusinessService.isExist(id)) {
            throw new ClientException(BUSINESS_NOT_FOUND);
        }
        return baseBusinessService.addComment(id, userId, comment);
    }

    @PutMapping("/comment")
    public CommentDto updateComment(@RequestBody @Valid CommentDto comment) {
        UUID userId = SecurityUtil.getUserId();
        if (userId == null) {
            throw new ClientException(ANONYMOUS_CANT_COMMENT);
        }
        return baseBusinessService.updateComment(userId, comment);
    }

    @DeleteMapping("/comment/{id}")
    public MapResponse deleteComment(@PathVariable("id") UUID id) {
        UUID userId = SecurityUtil.getUserId();
        if (userId == null) {
            throw new ClientException(ANONYMOUS_CANT_COMMENT);
        }
        baseBusinessService.deleteComment(id, userId);
        return new MapResponse("true");
    }

    //++++++++++++++ Elastic index +++++++++++++++++++++++

    @GetMapping("/indexing")
    public MapResponse indexing() {
        businessEsService.indexAll();
        return MapResponse.resultTrue();
    }

    //++++++++++++++ Search By +++++++++++++++++++++++

    @PostMapping("/search/document")
    public List<BusinessDocument> searchDocument(@Valid @RequestBody(required = false) BusinessSearchDto businessSearch) {
        return businessEsService.searchDocuments(businessSearch);
    }
    
    @PostMapping("/search/groups")
    public BusinessGroupDto searchGroup(@RequestBody BusinessGroupSearchDto businessGroupSearch) {
        return businessSearchFacade.getBusinessGroup(businessGroupSearch);
    }
    
    @PostMapping("/search/page")
    public Page<BusinessDocument> searchPage(@RequestBody(required = false) BusinessSearchDto businessSearch) {
        return businessEsService.searchDocumentsPage(businessSearch);
    }

    @GetMapping("full-model-by-id")
    public BusinessFullModel getFullModelById(@RequestParam("id") UUID id) {
        return baseBusinessService.getFullModelByIds(Arrays.asList(id)).get(0);
    }

    @GetMapping("/by-current-user/like-worker/full-model")
    public List<BusinessFullModel> getAllFullBusinessBy() {
        return baseBusinessService.getAllFullBusinessByCurrentUser();
    }

    @GetMapping("/by-current-user/like-worker")
    public List<BaseBusinessDto> getAllBusinessByUser() {
        return baseBusinessService.getAllBusinessByCurrentUser();
    }

    @GetMapping("/by-current-user/like-owner")
    public List<BaseBusinessDto> getByUserLikeOwner() {
        return baseBusinessService.getByCorporationIds(SecurityUtil.getUserCorporationIds());
    }

    @GetMapping("/by-corporation-id")
    public List<BaseBusinessDto> getByCorporationId(@RequestParam("id") UUID id) {
        return baseBusinessService.getByCorporationId(id);
    }

    //+++++++++++++ Customer ++++++++++++++++++++++++++++++++++++++

    @GetMapping("/customers")
    public Page<ClientDocument> getCustomersByBusinessIds(@RequestParam(value = "corporationId", required = false) UUID corporationId,
                                                          @RequestParam(value = "businessIds", required = false) List<UUID> businessIds,
                                                          @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(value = "size", required = false, defaultValue = "100") Integer size,
                                                          @RequestParam(value = "query", required = false) String query) {
        return clientSearchFacade.getCustomersByBusinessIdsOrCorporationId(businessIds, corporationId, page, size, query);
    }

    //++++++++++++++++++++ Tag ++++++++++++++++++++++++++++++++++

    @PostMapping("/tag/add")
    public List<TagDto> addTag(@RequestParam(value = "tagId") UUID tagId,
                               @RequestParam(value = "businessId") UUID businessId) {
        return baseBusinessService.addTag(tagId, businessId);
    }
    
    @PostMapping("/tag/save")
    public List<TagDto> saveTag(@RequestParam(value = "tagId") List<UUID> tagId,
                                @RequestParam(value = "businessId") UUID businessId) {
        return baseBusinessService.saveTags(tagId, businessId);
    }

    @PostMapping("/tag/remove")
    public List<TagDto> removeTag(@RequestParam(value = "tagId") UUID tagId,
                                  @RequestParam(value = "businessId") UUID businessId) {
        return baseBusinessService.removeTag(tagId, businessId);
    }
    
    @GetMapping("/{id}/tags")
    public List<TagDto> getTags(@PathVariable("id") UUID businessId) {
        return baseBusinessService.getTags(businessId);
    }
}
