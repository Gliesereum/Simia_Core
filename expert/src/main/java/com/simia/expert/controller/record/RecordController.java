package com.simia.expert.controller.record;

import com.simia.expert.service.record.BaseRecordService;
import com.simia.share.common.model.dto.expert.enumerated.StatusPay;
import com.simia.share.common.model.dto.expert.enumerated.StatusProcess;
import com.simia.share.common.model.dto.expert.record.BaseRecordDto;
import com.simia.share.common.model.dto.expert.record.RecordFreeTime;
import com.simia.share.common.model.dto.expert.record.RecordPaymentInfoDto;
import com.simia.share.common.model.dto.expert.record.RequestLiteRecordDto;
import com.simia.share.common.model.dto.expert.record.search.BusinessRecordSearchDto;
import com.simia.share.common.model.dto.expert.record.search.BusinessRecordSearchPageableDto;
import com.simia.share.common.model.dto.expert.record.search.ClientRecordSearchDto;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 * @since 12/7/18
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private BaseRecordService service;

    @GetMapping("/{id}")
    public BaseRecordDto getById(@PathVariable("id") UUID id) {
        return service.getFullModelByIdWithPermission(id);
    }

    @PostMapping
    public BaseRecordDto create(@Valid @RequestBody BaseRecordDto dto) {
        BaseRecordDto baseRecordDto = service.create(dto);
        if (baseRecordDto != null) {
            baseRecordDto = service.getFullModelById(baseRecordDto.getId());
        }
        return baseRecordDto;
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new MapResponse("true");
    }

    @PostMapping("/create-for-business")
    public BaseRecordDto createForBusiness(@Valid @RequestBody BaseRecordDto dto,
                                           @RequestParam(value = "isCustom", required = false, defaultValue = "false") Boolean isCustom) {
        return service.createForBusiness(dto, isCustom);
    }

    @PostMapping("/create-lite")
    public MapResponse createLite(@Valid @RequestBody RequestLiteRecordDto dto) {
        service.createLite(dto);
        return new MapResponse("true");
    }

    @PutMapping("/canceled-record")
    public BaseRecordDto canceledRecord(@RequestParam("idRecord") UUID idRecord,
                                        @RequestParam("message") String message) {
        return service.canceledRecord(idRecord, message);
    }

    @PutMapping("/update-time")
    public BaseRecordDto updateRecordTime(@RequestParam("idRecord") UUID idRecord,
                                          @RequestParam("beginTime") Long beginTime) {
        return service.updateRecordTime(idRecord, beginTime);
    }

    @PutMapping("/update-status-process")
    public BaseRecordDto updateStatusProcess(@RequestParam("idRecord") UUID idRecord,
                                             @RequestParam("status") StatusProcess status) {
        return service.updateStatusProgress(idRecord, status);
    }

    @PutMapping("/update-status-pay")
    public BaseRecordDto updateStatusPay(@RequestParam("idRecord") UUID idRecord,
                                         @RequestParam("status") StatusPay status) {
        return service.updateStatusPay(idRecord, status);
    }

    @GetMapping("/by-current-user")
    public Page<BaseRecordDto> getAllByCurrentUser(@RequestParam(value = "page", required = false) Integer page,
                                                   @RequestParam(value = "size", required = false) Integer size) {
        return service.getAllByUser(page, size);
    }

    @PostMapping("/by-params-for-current-user")
    public List<BaseRecordDto> getByParamsForCurrentClient(@RequestBody ClientRecordSearchDto search) {
        return service.getByParamsForClient(search);
    }

    @PostMapping("/by-params-for-business")
    public Page<BaseRecordDto> getByParamsForBusiness(@Valid @NotNull @RequestBody BusinessRecordSearchPageableDto search) {
        return service.getByParamsForBusiness(search);
    }

    @PostMapping("/by-params-for-business/payment-info")
    public RecordPaymentInfoDto getPaymentInfoByParamsForBusiness(@Valid @NotNull @RequestBody BusinessRecordSearchDto search) {
        return service.getPaymentInfoForBusiness(search);
    }

    @PostMapping("/free-time")
    public BaseRecordDto getFreeTimeForRecord(@RequestBody BaseRecordDto dto,
                                              @RequestParam(value = "isCustom", required = false, defaultValue = "false") Boolean isCustom) {
        return service.getFreeTimeForRecord(dto, isCustom);
    }

    @GetMapping("/workers-free-time")
    public Map<UUID, Set<RecordFreeTime>> getFreeTimes(@RequestParam("businessId") UUID businessId,
                                                       @RequestParam(value = "from", required = false) Long from,
                                                       @RequestParam(value = "workerId", required = false) UUID workerId) {
        return service.getFreeTimes(businessId, workerId, from);
    }
}
