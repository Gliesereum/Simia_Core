package com.simia.account.service.wallet;

import com.simia.account.model.entity.wallet.OperationStoryEntity;
import com.simia.share.common.model.dto.account.wallet.OperationStoryDto;
import com.simia.share.common.model.dto.account.wallet.TransactionDto;
import com.simia.share.common.service.auditable.AuditableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OperationStoryService extends AuditableService<OperationStoryDto, OperationStoryEntity> {

    Page<TransactionDto> getMyTransactions(Long fromDate, Long toDate, Pageable pageable);
}
