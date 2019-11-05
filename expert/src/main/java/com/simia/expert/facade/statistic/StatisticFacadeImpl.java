package com.simia.expert.facade.statistic;

import com.simia.expert.service.business.BaseBusinessService;
import com.simia.expert.service.business.WorkerService;
import com.simia.expert.service.record.BaseRecordService;
import com.simia.expert.service.service.ServicePriceService;
import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.exchange.service.account.AccountStatisticExchangeService;
import com.simia.share.common.model.dto.account.statistic.AccountPublicStatisticDto;
import com.simia.share.common.model.dto.expert.enumerated.StatusRecord;
import com.simia.share.common.model.dto.expert.record.search.BusinessRecordSearchDto;
import com.simia.share.common.model.dto.expert.statistic.KarmaPublicStatisticDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.concurrent.Future;

import static com.simia.share.common.exception.messages.CommonExceptionMessage.SERVER_ERROR;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Service
@Slf4j
public class StatisticFacadeImpl implements StatisticFacade {

    @Autowired
    private AccountStatisticExchangeService accountStatisticExchangeService;

    @Autowired
    private BaseBusinessService baseBusinessService;

    @Autowired
    private ServicePriceService servicePriceService;

    @Autowired
    private BaseRecordService baseRecordService;

    @Autowired
    private WorkerService workerService;

    //TODO:refactor on use Auditable
    @Override
    public KarmaPublicStatisticDto getPublicStatistic() {
        KarmaPublicStatisticDto result = new KarmaPublicStatisticDto();
        Future<AccountPublicStatisticDto> accountStatisticFuture = accountStatisticExchangeService.getPublicStatisticAsync();
        result.setBusinessCount(baseBusinessService.count());
        result.setServicePriceCount(servicePriceService.count());
        result.setRecordCount(baseRecordService.count());
        result.setRecordCompletedCount(baseRecordService.countByStatusRecord(StatusRecord.COMPLETED));
        result.setRecordCanceledCount(baseRecordService.countByStatusRecord(StatusRecord.CANCELED));
        result.setRecordWaitingCount(baseRecordService.countByStatusRecord(StatusRecord.CREATED));
        result.setRecordPriceSum(baseRecordService.getPriceSum(null));
        result.setRecordCompletedPriceSum(baseRecordService.getPriceSum(buildSearchByStatus(StatusRecord.COMPLETED)));
        result.setRecordWaitingPriceSum(baseRecordService.getPriceSum(buildSearchByStatus(StatusRecord.CREATED)));
        result.setRecordCanceledPriceSum(baseRecordService.getPriceSum(buildSearchByStatus(StatusRecord.CANCELED)));

        long countBusyWorker = baseRecordService.countBusyWorker(LocalDateTime.now(ZoneId.of("UTC")), StatusRecord.CREATED);
        long countAllWorker = workerService.count();
        long countFreeWorker = countAllWorker - countBusyWorker;
        result.setWorkerBusyCount(countBusyWorker);
        result.setWorkerFreeCount(countFreeWorker);
        result.setWorkerCount(countAllWorker);
        try {
            AccountPublicStatisticDto accountPublicStatisticDto = accountStatisticFuture.get();
            result.setUserCount(accountPublicStatisticDto.getUserCount());
            result.setCorporationCount(accountPublicStatisticDto.getCorporationCount());
        } catch (Exception e) {
            log.error("Error while get value from future");
            throw new ClientException(SERVER_ERROR);
        }
        return result;
    }

    private BusinessRecordSearchDto buildSearchByStatus(StatusRecord statusRecord) {
        BusinessRecordSearchDto businessRecordSearchDto = new BusinessRecordSearchDto();
        businessRecordSearchDto.setStatus(Arrays.asList(statusRecord));
        return businessRecordSearchDto;
    }
}
