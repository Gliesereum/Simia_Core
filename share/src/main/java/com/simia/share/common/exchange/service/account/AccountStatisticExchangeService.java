package com.simia.share.common.exchange.service.account;

import com.simia.share.common.model.dto.account.statistic.AccountPublicStatisticDto;

import java.util.concurrent.Future;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface AccountStatisticExchangeService {

    AccountPublicStatisticDto getPublicStatistic();

    Future<AccountPublicStatisticDto> getPublicStatisticAsync();
}
