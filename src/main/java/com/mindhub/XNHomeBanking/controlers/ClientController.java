package com.mindhub.XNHomeBanking.controlers;

import com.mindhub.XNHomeBanking.dto.ClientDTO;
import com.mindhub.XNHomeBanking.models.Account;
import com.mindhub.XNHomeBanking.models.Client;
import com.mindhub.XNHomeBanking.repositories.AccountRepositories;
import com.mindhub.XNHomeBanking.repositories.ClientsRepositories;
import jakarta.persistence.*;
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
    @Autowired
    private ClientsRepositories clientsRepositories;
    //Servlet (microprograma que responde peticiones específicas.)
    @Autowired
    private AccountRepositories accountRepositories;

    @RequestMapping("/all/clients")
    public List<ClientDTO> getAllClients() {
        return clientsRepositories.findAll()
                .stream()
                .map(client -> new ClientDTO(client))
                .collect(Collectors.toList());
    }

    //Servlet (microprograma que responde peticiones específicas.)
    @RequestMapping("/clients/{id}")
    public ClientDTO getOneClient(@PathVariable Long id) {
        return new ClientDTO(clientsRepositories.findById(id).orElse(null));
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/clients")
    public ResponseEntity<String> createClient(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String password) {
        if (firstname.isBlank()) {
            return new ResponseEntity<>("El nombre no puede estar vacio", HttpStatus.FORBIDDEN); //403
        }
        if (lastname.isBlank()) {
            return new ResponseEntity<>("El apellido no puede estar vacio", HttpStatus.FORBIDDEN);//403
        }
        if (email.isBlank()) {
            return new ResponseEntity<>("El correo no puede estar vacio", HttpStatus.FORBIDDEN);//403
        }
        if (password.isBlank()) {
            return new ResponseEntity<>("La contraseña no puede estar vacia", HttpStatus.FORBIDDEN);//403
        }
        if (clientsRepositories.existsByEmail(email)) {
            return new ResponseEntity<>("El email ya esta registrado", HttpStatus.FORBIDDEN);
        }


        Client client = new Client(firstname, lastname, email, passwordEncoder.encode(password));
        clientsRepositories.save(client);
        String numberAccount;
        do {
            numberAccount = "VIN-" + getRandomNumber(00000000, 99999999);
        } while (accountRepositories.existsBynumber(numberAccount));

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
        Client client = clientsRepositories.findByEmail(authentication.getName());
        //Cuando este se realiza la autenticacion y es exitosa, se inicia la session generandose un nuevo TOKKEN
        ClientDTO clientDTO = new ClientDTO(client);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

}//Aca terminar la clase ClientController.