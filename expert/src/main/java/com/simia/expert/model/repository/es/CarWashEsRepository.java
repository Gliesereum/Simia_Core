package com.simia.expert.model.repository.es;

import com.simia.expert.model.document.BusinessDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface CarWashEsRepository extends ElasticsearchRepository<BusinessDocument, String> {
}
