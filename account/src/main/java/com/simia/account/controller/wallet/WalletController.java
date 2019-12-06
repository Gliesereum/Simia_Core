package com.simia.account.controller.wallet;

import com.simia.account.service.wallet.OperationStoryService;
import com.simia.account.service.wallet.WalletService;
import com.simia.share.common.model.dto.account.enumerated.TransactionResultType;
import com.simia.share.common.model.dto.account.wallet.TransactionDto;
import com.simia.share.common.model.dto.account.wallet.TransactionRequestDto;
import com.simia.share.common.model.dto.account.wallet.WalletDto;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService service;

    @Autowired
    private OperationStoryService operationStoryService;

    @GetMapping
    public List<WalletDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public WalletDto getById(@PathVariable("id") UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public WalletDto create(@Valid @RequestBody WalletDto dto) {
        return service.create(dto);
    }

    @PutMapping
    public WalletDto update(@Valid @RequestBody WalletDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public MapResponse delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new MapResponse("true");
    }

    @PostMapping("/make-transaction")
    public TransactionResultType makeTransaction(@Valid @RequestBody TransactionRequestDto request) {
        return service.makeTransaction(request);
    }

    @GetMapping("/get-my-transactions")
    public Page<TransactionDto> getMyTransactions(@RequestParam(value = "fromDate", required = false) Long fromDate,
                                                  @RequestParam(value = "toDate", required = false) Long toDate,
                                                  @PageableDefault(page = 0,
                                                          size = 50,
                                                          sort = "createDate",
                                                          direction = Sort.Direction.DESC) Pageable pageable) {
        return operationStoryService.getMyTransactions(fromDate, toDate, pageable);
    }
}
