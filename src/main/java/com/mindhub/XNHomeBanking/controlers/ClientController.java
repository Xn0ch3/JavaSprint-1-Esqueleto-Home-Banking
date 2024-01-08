package com.mindhub.XNHomeBanking.controlers;

import com.mindhub.XNHomeBanking.Service.ClientService;
import com.mindhub.XNHomeBanking.dto.ClientDTO;
import com.mindhub.XNHomeBanking.dto.CreateClientDTO;
import com.mindhub.XNHomeBanking.models.Account;
import com.mindhub.XNHomeBanking.models.Client;
import com.mindhub.XNHomeBanking.repositories.AccountRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


//Controlador, escucha y responde peticiones.
@RestController
@RequestMapping("/api")
public class ClientController {

    // de DependenciasInyección
    //Servlet (microprograma que responde peticiones específicas.)
    @Autowired
    private AccountRepositories accountRepositories;

    @Autowired
    private ClientService clientService;

    @RequestMapping("/all/clients")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClientsDTO();
    }


    //Servlet (microprograma que responde peticiones específicas.)
    //@RequestMapping("/clients/{id}")
   // public ClientDTO getOneClient(@PathVariable Long id) {
        //return new ClientDTO(clientsRepositories.findById(id).orElse(null));
    //}

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/clients")
    public ResponseEntity<String> createClient(@RequestBody CreateClientDTO createClientDTO) {
        if (createClientDTO.getFirstname().isBlank()) {
            return new ResponseEntity<>("El nombre no puede estar vacio", HttpStatus.FORBIDDEN); //403
        }
        if (createClientDTO.getLastname().isBlank()) {
            return new ResponseEntity<>("El apellido no puede estar vacio", HttpStatus.FORBIDDEN);//403
        }
        if (createClientDTO.getEmail().isBlank()) {
            return new ResponseEntity<>("El correo no puede estar vacio", HttpStatus.FORBIDDEN);//403
        }
        if (createClientDTO.getPassword().isBlank()) {
            return new ResponseEntity<>("La contraseña no puede estar vacia", HttpStatus.FORBIDDEN);//403
        }
        if (clientService.existsByEmail(createClientDTO.getEmail())) {
            return new ResponseEntity<>("El email ya esta registrado", HttpStatus.FORBIDDEN);
        }


        Client client = new Client(createClientDTO.getFirstname(), createClientDTO.getLastname(), createClientDTO.getEmail(), passwordEncoder.encode(createClientDTO.getPassword()));
        clientService.saveClient(client);
        String numberAccount;
        do {
            numberAccount = "VIN-" + getRandomNumber(00000000, 99999999);
        } while (accountRepositories.existsByNumber(numberAccount));

        Account account = new Account(numberAccount, LocalDate.now(), (double) 0);
        client.addAccount(account);
        accountRepositories.save(account);
        return new ResponseEntity<>("Registrado con exito", HttpStatus.CREATED);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @GetMapping("/clients/current")
    public ResponseEntity<Object> getOneClient(Authentication authentication) {
        Client client = clientService.findByEmail(authentication.getName());
        //Cuando este se realiza la autenticacion y es exitosa, se inicia la session generandose un nuevo TOKKEN
        ClientDTO clientDTO = new ClientDTO(client);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

}//Aca terminar la clase ClientController.