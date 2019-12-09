package com.simia.share.common.model.dto.expert.category;

import com.simia.share.common.model.dto.AuditableDefaultDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryDto extends AuditableDefaultDto {

    @NonNull
    private String title;

    private String iconUrl;

    private UUID parentId;

    private List<CategoryDto> childCategories = new ArrayList<>();
}
