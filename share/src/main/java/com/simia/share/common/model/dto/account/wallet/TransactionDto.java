package com.simia.share.common.model.dto.account.wallet;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simia.share.common.databind.json.LocalDateTimeJsonDeserializer;
import com.simia.share.common.databind.json.LocalDateTimeJsonSerializer;
import com.simia.share.common.model.dto.account.enumerated.TransactionResultType;
import com.simia.share.common.model.dto.account.user.PublicUserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TransactionDto {

    private UUID userFromId;

    private PublicUserDto userFrom;

    private UUID userToId;

    private PublicUserDto userTo;

    private Double amount;

    private TransactionResultType transactionResultType;

    @JsonDeserialize(using = LocalDateTimeJsonDeserializer.class)
    @JsonSerialize(using = LocalDateTimeJsonSerializer.class)
    private LocalDateTime operationDate;
}
