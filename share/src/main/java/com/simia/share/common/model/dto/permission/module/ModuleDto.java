package com.simia.share.common.model.dto.permission.module;

import com.simia.share.common.model.dto.DefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ModuleDto extends DefaultDto {

    @NotEmpty
    private String title;

    private String description;

    @NotEmpty
    private String url;

    @NotEmpty
    private String version;

    private Boolean isActive;

    private String inactiveMessage;
}
