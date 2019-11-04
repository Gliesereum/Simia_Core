package com.simia.share.common.model.dto.karma.tag;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagDto extends AuditableDefaultDto {

    @NotNull
    private String name;

    private String description;
}
