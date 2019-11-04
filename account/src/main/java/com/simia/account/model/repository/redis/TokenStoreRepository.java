package com.simia.account.model.repository.redis;

import com.simia.account.model.domain.TokenStoreDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yvlasiuk
 * @version 1.0
 */
@Repository
public interface TokenStoreRepository extends CrudRepository<TokenStoreDomain, String> {

    TokenStoreDomain findByRefreshToken(String refreshToken);

    List<TokenStoreDomain> findByUserId(String userId);
}
