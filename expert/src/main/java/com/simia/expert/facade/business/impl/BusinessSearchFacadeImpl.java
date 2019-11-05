package com.simia.expert.facade.business.impl;

import com.simia.expert.facade.business.BusinessSearchFacade;
import com.simia.expert.model.document.BusinessDocument;
import com.simia.expert.model.repository.jpa.record.BaseRecordRepository;
import com.simia.expert.service.business.WorkerService;
import com.simia.expert.service.es.BusinessEsService;
import com.simia.expert.service.popular.BusinessPopularService;
import com.simia.expert.service.service.PackageService;
import com.simia.expert.service.service.ServicePriceService;
import com.simia.expert.service.tag.TagService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.model.dto.expert.business.LiteWorkerDto;
import com.simia.share.common.model.dto.expert.business.document.BusinessDocumentDto;
import com.simia.share.common.model.dto.expert.business.group.BusinessGroupDto;
import com.simia.share.common.model.dto.expert.business.group.BusinessGroupListItemDto;
import com.simia.share.common.model.dto.expert.business.group.BusinessGroupTagDto;
import com.simia.share.common.model.dto.expert.business.group.enumerated.BusinessGroupBy;
import com.simia.share.common.model.dto.expert.business.popular.BusinessPopularDto;
import com.simia.share.common.model.dto.expert.business.search.BusinessGroupSearchDto;
import com.simia.share.common.model.dto.expert.record.RecordUsageCountDto;
import com.simia.share.common.model.dto.expert.service.LitePackageDto;
import com.simia.share.common.model.dto.expert.service.LiteServicePriceDto;
import com.simia.share.common.model.dto.expert.tag.TagDto;
import com.simia.share.common.model.enumerated.ObjectState;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BusinessSearchFacadeImpl implements BusinessSearchFacade {
    
    @Autowired
    private DefaultConverter defaultConverter;
    
    @Autowired
    private BusinessEsService businessEsService;
    
    @Autowired
    private TagService tagService;
    
    @Autowired
    private BaseRecordRepository baseRecordRepository;
    
    @Autowired
    private PackageService packageService;
    
    @Autowired
    private WorkerService workerService;
    
    @Autowired
    private ServicePriceService servicePriceService;
    
    @Autowired
    private BusinessPopularService businessPopularService;
    
    @Override
    public BusinessGroupDto getBusinessGroup(BusinessGroupSearchDto groupSearch) {
        BusinessGroupDto result = null;
        if (groupSearch != null) {
            Page<BusinessDocument> businessDocuments = businessEsService.searchDocumentsPage(groupSearch);
            result = new BusinessGroupDto();
            Page<BusinessDocumentDto> documentDtoPage = defaultConverter.convert(businessDocuments, BusinessDocumentDto.class);
            result.setPage(documentDtoPage);
            
            List<BusinessDocumentDto> businesses = null;
            if (documentDtoPage != null) {
                businesses = documentDtoPage.getContent();
            }
            
            if ((groupSearch.getSize() != null) || (groupSearch.getPage() != null)) {
                groupSearch.setSize(null);
                groupSearch.setPage(null);
                List<BusinessDocument> searchDocuments = businessEsService.searchDocuments(groupSearch);
                businesses = defaultConverter.convert(searchDocuments, BusinessDocumentDto.class);
            }
            
            result.setGroups(new HashMap<>());
            if (CollectionUtils.isNotEmpty(businesses)) {
                long limit = groupSearch.getCountInGroups() != null ? groupSearch.getCountInGroups() : 10L;
                Map<UUID, BusinessDocumentDto> businessMap = businesses.stream().collect(Collectors.toMap(i -> UUID.fromString(i.getId()), i -> i));
                
                addTagGroups(groupSearch, businesses, result);
                addGroup(groupSearch, businessMap, result, limit);
            }
        }
        return result;
    }
    
    private void addGroup(BusinessGroupSearchDto groupSearch, Map<UUID, BusinessDocumentDto> businessMap, BusinessGroupDto target, long limit) {
        List<BusinessGroupBy> groups = groupSearch.getGroups();
        LocalDateTime dateFromSearch = LocalDateTime.now(ZoneId.of("UTC")).minusMonths(1);
        if (groupSearch.getDateFromRecordSearch() != null) {
            dateFromSearch = groupSearch.getDateFromRecordSearch();
        }
        if (CollectionUtils.isNotEmpty(groups)) {
            for (BusinessGroupBy group : groups) {
                switch (group) {
                    case orderByRating:
                        addGroupOrderByRating(businessMap, target, group, limit, groupSearch.getMinimumRatingCount());
                        break;
                    case orderByPopular:
                        addGroupOrderByPopular(businessMap, target, group, limit);
                        break;
                    case orderByPopularPackage:
                        addGroupOrderByPopularPackage(businessMap, target, group, limit, dateFromSearch);
                        break;
                    case orderByPopularService:
                        addGroupOrderByPopularService(businessMap, target, group, limit, dateFromSearch);
                        break;
                    case orderByRecordCount:
                        addGroupOrderByRecordCount(businessMap, target, group, limit, dateFromSearch);
                        break;
                    case orderByPopularWorker:
                        addGroupOrderByPopularWorker(businessMap, target, group, limit, dateFromSearch);
                        break;
                }
            }
            
        }
    }
    
    private void addGroupOrderByPopularPackage(Map<UUID, BusinessDocumentDto> businessMap, BusinessGroupDto target, BusinessGroupBy businessGroup, long limit, LocalDateTime dateFromSearch) {
        Set<UUID> businessIds = businessMap.keySet();
        List<RecordUsageCountDto> usageCount = baseRecordRepository.getCountPackageUsage(dateFromSearch, businessIds, limit);
        List<BusinessGroupListItemDto> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(usageCount)) {
            List<UUID> packageIds = usageCount.stream().map(RecordUsageCountDto::getObjectId).collect(Collectors.toList());
            Map<UUID, LitePackageDto> packageMap = packageService.getMapByIds(packageIds);
            list = usageCount.stream().map(i -> {
                BusinessGroupListItemDto item = new BusinessGroupListItemDto();
                item.setBusiness(businessMap.get(i.getBusinessId()));
                item.setCount(i.getCount());
                item.setObject(packageMap.get(i.getObjectId()));
                return item;
            }).collect(Collectors.toList());
        }
        target.getGroups().put(businessGroup, list);
    }
    
    private void addGroupOrderByPopular(Map<UUID, BusinessDocumentDto> businessMap, BusinessGroupDto target, BusinessGroupBy businessGroup, long limit) {
        Set<UUID> businessIds = businessMap.keySet();
        List<BusinessPopularDto> businessPopular = businessPopularService.getByBusinessIds(businessIds, (int) limit);
        List<BusinessGroupListItemDto> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(businessPopular)) {
            list = businessPopular.stream().map(i -> {
                BusinessGroupListItemDto item = new BusinessGroupListItemDto();
                item.setBusiness(businessMap.get(i.getBusinessId()));
                item.setCount(i.getCount());
                return item;
            }).collect(Collectors.toList());
        }
        target.getGroups().put(businessGroup, list);
    }
    
    private void addGroupOrderByPopularService(Map<UUID, BusinessDocumentDto> businessMap, BusinessGroupDto target, BusinessGroupBy businessGroup, long limit, LocalDateTime dateFromSearch) {
        Set<UUID> businessIds = businessMap.keySet();
        List<RecordUsageCountDto> usageCount = baseRecordRepository.getCountServiceUsage(dateFromSearch, businessIds, limit);
        List<BusinessGroupListItemDto> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(usageCount)) {
            List<UUID> serviceIds = usageCount.stream().map(RecordUsageCountDto::getObjectId).collect(Collectors.toList());
            Map<UUID, LiteServicePriceDto> serviceMap = servicePriceService.getMapByIds(serviceIds);
            list = usageCount.stream().map(i -> {
                BusinessGroupListItemDto item = new BusinessGroupListItemDto();
                item.setBusiness(businessMap.get(i.getBusinessId()));
                item.setCount(i.getCount());
                item.setObject(serviceMap.get(i.getObjectId()));
                return item;
            }).collect(Collectors.toList());
        }
        target.getGroups().put(businessGroup, list);
    }
    
    private void addGroupOrderByPopularWorker(Map<UUID, BusinessDocumentDto> businessMap, BusinessGroupDto target, BusinessGroupBy businessGroup, long limit, LocalDateTime dateFromSearch) {
        Set<UUID> businessIds = businessMap.keySet();
        List<RecordUsageCountDto> usageCount = baseRecordRepository.getCountWorkerUsage(dateFromSearch, businessIds, limit);
        List<BusinessGroupListItemDto> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(usageCount)) {
            List<UUID> workerIds = usageCount.stream().map(RecordUsageCountDto::getObjectId).collect(Collectors.toList());
            Map<UUID, LiteWorkerDto> workerMap = workerService.getLiteWorkerMapByIds(workerIds);
            
            list = usageCount.stream().map(i -> {
                BusinessGroupListItemDto item = new BusinessGroupListItemDto();
                item.setBusiness(businessMap.get(i.getBusinessId()));
                item.setCount(i.getCount());
                item.setObject(workerMap.get(i.getObjectId()));
                return item;
            }).collect(Collectors.toList());
        }
        target.getGroups().put(businessGroup, list);
    }
    
    private void addGroupOrderByRecordCount(Map<UUID, BusinessDocumentDto> businessMap, BusinessGroupDto target, BusinessGroupBy businessGroup, long limit, LocalDateTime dateFromSearch) {
        Set<UUID> businessIds = businessMap.keySet();
        List<RecordUsageCountDto> usageCount = baseRecordRepository.getCountRecordInBusiness(dateFromSearch, businessIds, limit);
        List<BusinessGroupListItemDto> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(usageCount)) {
            list = usageCount.stream().map(i -> {
                BusinessGroupListItemDto item = new BusinessGroupListItemDto();
                item.setBusiness(businessMap.get(i.getBusinessId()));
                item.setCount(i.getCount());
                return item;
            }).collect(Collectors.toList());
        }
        target.getGroups().put(businessGroup, list);
    }
    
    private void addGroupOrderByRating(Map<UUID, BusinessDocumentDto> businessMap, BusinessGroupDto target, BusinessGroupBy businessGroup, long limit, Integer minimumRatingCount) {
	    List<BusinessGroupListItemDto> list;
	    Stream<BusinessDocumentDto> stream = businessMap.values().stream();
	    if (minimumRatingCount != null) {
	    	stream = stream.filter(i -> (i.getRatingCount() != null) &&  (i.getRatingCount() >= minimumRatingCount));
	    }
	    list = stream.sorted(Comparator.comparingDouble(BusinessDocumentDto::getRating).reversed())
			    .limit(limit)
			    .map(i -> {
				    BusinessGroupListItemDto item = new BusinessGroupListItemDto();
				    item.setBusiness(i);
				    return item;
			    })
			    .collect(Collectors.toList());
	    target.getGroups().put(businessGroup, list);
    }
    
    private void addTagGroups(BusinessGroupSearchDto groupSearch, List<BusinessDocumentDto> businesses, BusinessGroupDto target) {
        if (CollectionUtils.isNotEmpty(businesses) && (groupSearch != null) && CollectionUtils.isNotEmpty(groupSearch.getTagGroups())) {
            List<String> tagNameGroups = groupSearch.getTagGroups();
            List<TagDto> tags = tagService.getTagsByName(tagNameGroups, Arrays.asList(ObjectState.ACTIVE));
            Map<String, List<BusinessDocumentDto>> tagBusiness = new HashMap<>();
            for (BusinessDocumentDto business : businesses) {
                List<String> businessTags = business.getTags();
                if (CollectionUtils.isNotEmpty(businessTags)) {
                    for (String businessTag : businessTags) {
                        tagBusiness.putIfAbsent(businessTag, new ArrayList<>());
                        tagBusiness.get(businessTag).add(business);
                    }
                }
            }
            Map<String, BusinessGroupTagDto> tagGroups = new HashMap<>();
            if (CollectionUtils.isNotEmpty(tags)) {
                for (TagDto tag : tags) {
                    BusinessGroupTagDto businessGroupTagDto = new BusinessGroupTagDto();
                    businessGroupTagDto.setTag(tag);
                    businessGroupTagDto.setBusiness(tagBusiness.get(tag.getName()));
                    tagGroups.put(tag.getName(), businessGroupTagDto);
                }
            }
            target.setTagGroups(tagGroups);
        }
    }
}
