package com.simia.expert.controller.statistic;

import com.simia.expert.facade.statistic.StatisticFacade;
import com.simia.share.common.model.dto.expert.statistic.KarmaPublicStatisticDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticFacade statisticFacade;

    @GetMapping("/public")
    public KarmaPublicStatisticDto getPublicStatistic() {
        return statisticFacade.getPublicStatistic();
    }
}
