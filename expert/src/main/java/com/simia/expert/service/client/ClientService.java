package com.simia.expert.service.client;

import com.simia.expert.model.entity.client.ClientEntity;
import com.simia.share.common.model.dto.account.user.PublicUserDto;
import com.simia.share.common.model.dto.account.user.UserDto;
import com.simia.share.common.model.dto.expert.business.LiteBusinessDto;
import com.simia.share.common.model.dto.expert.client.ClientDto;
import com.simia.share.common.service.auditable.AuditableService;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface ClientService extends AuditableService<ClientDto, ClientEntity> {

    ClientDto addNewClient(PublicUserDto user, UUID businessId);
    
    void importClients(List<Pair<PublicUserDto, List<LiteBusinessDto>>> userPair);

    ClientDto addNewClient(UserDto user, UUID businessId);

    ClientDto updateClientInfo(UserDto user);

    Map<UUID, ClientDto> getClientMapByIds(Collection<UUID> ids);

    List<ClientDto> getClientByIds(Collection<UUID> ids);

    ClientDto getByUserId(UUID userId);
}
