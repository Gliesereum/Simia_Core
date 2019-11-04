package com.simia.account.model.repository.redis;

import com.simia.account.model.domain.VerificationDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vitalij
 */
@Repository
public interface VerificationRepository extends CrudRepository<VerificationDomain, String> {
}
