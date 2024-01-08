package com.mindhub.XNHomeBanking.Service;

import com.mindhub.XNHomeBanking.dto.ClientDTO;
import com.mindhub.XNHomeBanking.models.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();

    List<ClientDTO> getAllClientsDTO();

    boolean existsByEmail (String email);

    void saveClient(Client client);

    Client findByEmail(String email);

    Client getAutenticatedClient(String name);
}
