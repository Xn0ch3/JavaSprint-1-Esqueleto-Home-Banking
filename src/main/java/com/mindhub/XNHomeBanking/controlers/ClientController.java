package com.mindhub.XNHomeBanking.controlers;

import com.mindhub.XNHomeBanking.dto.ClientDTO;
import com.mindhub.XNHomeBanking.models.Client;
import com.mindhub.XNHomeBanking.repositories.ClientsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//Controlador, escucha y responde peticiones.
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    //Inyección de Dependencias
    @Autowired
    private ClientsRepositories clientsRepositories;
    @RequestMapping("/all")
    public List<ClientDTO> getAllClients(){
        return clientsRepositories.findAll()
                .stream()
                .map(client -> new ClientDTO(client))
                .collect(Collectors.toList());
    }
    @RequestMapping("/{id}")
    public ClientDTO getOneClient(@PathVariable Long id){
        return new ClientDTO(clientsRepositories.findById(id).orElse(null));
    }


    //Inyección por Constructor
    //public ClientController (ClientsRepositories clientsRepositories){
       // this.clientsRepositories = clientsRepositories;
    //}

}//Aca terminar la clase ClientController.