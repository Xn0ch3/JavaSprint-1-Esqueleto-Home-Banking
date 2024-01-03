package com.mindhub.XNHomeBanking.controlers;

import com.mindhub.XNHomeBanking.dto.AccountDTO;
import com.mindhub.XNHomeBanking.dto.ClientDTO;
import com.mindhub.XNHomeBanking.dto.TransactionDTO;
import com.mindhub.XNHomeBanking.models.Account;
import com.mindhub.XNHomeBanking.models.Client;
import com.mindhub.XNHomeBanking.repositories.AccountRepositories;
import com.mindhub.XNHomeBanking.repositories.ClientsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
//RestController marca la clase como un controlador donde cada método devuelve un objeto de dominio en lugar de una vista.
@RestController
//RequestMapping se utiliza para asignar solicitudes web a clases de controlador específicas y/o métodos de controlador.
//Request = Petición, Mapping = asociar. //Asocia una petición.
@RequestMapping("/api")
public class AccountController {
    //Autowired es una anotación utilizada en Spring Boot para habilitar la inyección automática de dependencias.
    @Autowired
    private AccountRepositories accountRepositories;
    @Autowired
    private ClientsRepositories clientsRepositories;

    @RequestMapping("/all/accounts")
    public List<ClientDTO> getAllClients() {
        return clientsRepositories.findAll()
                .stream()
                .map(client -> new ClientDTO(client))
                .collect(Collectors.toList());
    }

    @GetMapping("/accounts/{id}/transactions")
    public List<TransactionDTO> getOneAccount(@PathVariable Long id) {
        return accountRepositories.findById(id)
                .map(account -> account.getTransactionSet().stream()
                        .map(TransactionDTO::new)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

    }
    //Sprint7
    //Creamos un metodo para Crear cuentas
    @PostMapping("/clients/current/accounts")
    public ResponseEntity<String> CreateAccount(Authentication authentication) {

        Client client = clientsRepositories.findByEmail(authentication.getName());
        //Creamos el una condicion para saber si tiene Cuentas, y si tiene más 3.
        if (client.getListAccount().size() > 2 ){
            return new ResponseEntity<>("You already have 3 accounts, it is the maximum per customer.", HttpStatus.FORBIDDEN);
        }
        //Creamos un String para posteriormente asignarle un número aleatorio.
        String numberAccount;
        do{
            numberAccount = "VIN-" + getRandomNumber(00000000 , 99999999);
        }while (accountRepositories.existsBynumber(numberAccount));

        Account account = new Account(numberAccount , LocalDate.now(), (double) 0);
        clientsRepositories.save(client);
        client.addAccount(account);
        accountRepositories.save(account);


        return new ResponseEntity<>("Account Created", HttpStatus.CREATED);
    }

    //Creamos el método gerRandomNumber para generar numeros aleatorios para las cuentas.
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    //Sprint8
}//Aca termina la class AccountController.