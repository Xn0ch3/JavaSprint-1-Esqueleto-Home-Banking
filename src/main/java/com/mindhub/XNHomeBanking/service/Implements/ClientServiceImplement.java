package com.mindhub.XNHomeBanking.service.Implements;


import com.mindhub.XNHomeBanking.service.ClientService;
import com.mindhub.XNHomeBanking.dto.ClientDTO;
import com.mindhub.XNHomeBanking.models.Client;
import com.mindhub.XNHomeBanking.repositories.ClientsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    private ClientsRepositories clientsRepositories;



    @Override
    public List<ClientDTO> getAllClientsDTO() {
        return getAllClients().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @Override
    public boolean existsByEmail(String email) {
        return clientsRepositories.existsByEmail(email);
    }

    @Override
    public void saveClient(Client client) {
        clientsRepositories.save(client);
    }

    @Override
    public Client findByEmail(String email) {
        return clientsRepositories.findByEmail(email);
    }

    @Override
    public Client getAutenticatedClient(String name) {
        return clientsRepositories.findByEmail(name);
    }


    @Override
    public List<Client> getAllClients() {
        return clientsRepositories.findAll();
    }


}
