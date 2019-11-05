package com.simia.expert.facade.client;

import com.simia.share.common.model.dto.account.user.PublicUserDto;
import com.simia.share.common.model.dto.account.user.UserDto;
import com.simia.share.common.model.dto.expert.client.ClientDto;

import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public interface ClientFacade {
    
    void addNewClient(PublicUserDto user, UUID businessId);
    
    void addNewClient(UserDto user, UUID businessId);
    
    ClientDto addNewClientAddGet(PublicUserDto user, UUID businessId);
    
    void updateClientInfo(UserDto user);
    
    void importClients();
    
    void indexingClients();
}
