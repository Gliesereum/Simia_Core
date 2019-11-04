package com.simia.account.facade.statistic.impl;

import com.simia.account.facade.statistic.StatisticFacade;
import com.simia.account.service.user.CorporationService;
import com.simia.account.service.user.UserService;
import com.simia.share.common.model.dto.account.statistic.AccountPublicStatisticDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
public class StatisticFacadeImpl implements StatisticFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private CorporationService corporationService;

    //TODO:refactor on use Auditable
    @Override
    public AccountPublicStatisticDto getStatistic() {
        AccountPublicStatisticDto result = new AccountPublicStatisticDto();
        result.setUserCount(userService.count());
        result.setCorporationCount(corporationService.count());
        return result;
    }
}
