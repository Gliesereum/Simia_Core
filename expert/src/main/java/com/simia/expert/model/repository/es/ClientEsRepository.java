package com.simia.expert.model.repository.es;

import com.simia.expert.model.document.ClientDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author vitalij
 * @version 1.0
 */
public interface ClientEsRepository extends ElasticsearchRepository<ClientDocument, String> {
}
