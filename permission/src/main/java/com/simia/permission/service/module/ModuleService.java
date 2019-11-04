package com.simia.permission.service.module;

import com.simia.permission.model.entity.module.ModuleEntity;
import com.simia.share.common.model.dto.permission.module.ModuleDto;
import com.simia.share.common.service.DefaultService;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface ModuleService extends DefaultService<ModuleDto, ModuleEntity> {

    ModuleDto getByUrl(String url);

    ModuleDto getByUrlOrCreate(String url, String name);
}
