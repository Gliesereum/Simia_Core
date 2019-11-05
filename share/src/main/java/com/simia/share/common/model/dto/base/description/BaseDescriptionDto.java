package com.simia.share.common.model.dto.base.description;

import com.simia.share.common.model.dto.DefaultDto;
import com.simia.share.common.model.dto.expert.enumerated.LanguageCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseDescriptionDto extends DefaultDto {

    @NotNull
    private LanguageCode languageCode;

    private UUID objectId;
}
