package com.simia.share.common.model.dto.account.wallet;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@NoArgsConstructor
public class TransactionRequestDto {

    @NonNull
    private UUID userFromId;

    @NonNull
    private UUID userToId;

    @NonNull
    private Double amount;
}
