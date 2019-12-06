package com.simia.account.model.repository.jpa.wallet;

import com.simia.account.model.entity.wallet.OperationStoryEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OperationStoryRepository extends AuditableRepository<OperationStoryEntity> {

    Page<OperationStoryEntity> getByWalletFromIdOrWalletToIdAndCreateDateBetweenAndObjectState(UUID walletFromId,
                                                                                               UUID walletToId,
                                                                                               LocalDateTime fromDate,
                                                                                               LocalDateTime toDate,
                                                                                               ObjectState state,
                                                                                               Pageable pageable);

}
