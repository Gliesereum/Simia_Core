package com.simia.share.common.model.dto.lendinggallery.artbond;

import com.simia.share.common.model.dto.DefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArtBondTokenDto extends DefaultDto {

    private String blockchain;

    private Integer stockCount;

    private UUID artBondId;
}
