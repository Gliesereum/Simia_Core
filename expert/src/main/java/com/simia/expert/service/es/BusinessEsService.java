package com.simia.expert.service.es;

import com.simia.expert.model.document.BusinessDocument;
import com.simia.share.common.model.dto.expert.business.BaseBusinessDto;
import com.simia.share.common.model.dto.expert.business.BusinessSearchDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface BusinessEsService {

    List<BaseBusinessDto> search(BusinessSearchDto businessSearch);

    List<BusinessDocument> searchDocuments(BusinessSearchDto businessSearch);
    
    Page<BusinessDocument> searchDocumentsPage(BusinessSearchDto businessSearch);

    void indexAll();

    void indexAllAsync();

    void indexAsync(UUID businessId);

    void indexAsync(List<UUID> businessIds);
}
