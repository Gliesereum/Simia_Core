package com.simia.expert.controller.client;

import com.simia.expert.facade.client.ClientFacade;
import com.simia.share.common.model.response.MapResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
    
    @Autowired
    private ClientFacade clientFacade;
    
    @GetMapping("/import")
    public MapResponse importClient() {
        clientFacade.importClients();
        return MapResponse.resultTrue();
    }
    
    @GetMapping("/indexing")
    public MapResponse indexClients() {
        clientFacade.indexingClients();
        return MapResponse.resultTrue();
    }
}
