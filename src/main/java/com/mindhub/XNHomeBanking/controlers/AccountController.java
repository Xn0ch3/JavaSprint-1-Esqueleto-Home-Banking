package com.mindhub.XNHomeBanking.controlers;

import com.mindhub.XNHomeBanking.dto.AccountDTO;
import com.mindhub.XNHomeBanking.dto.ClientDTO;
import com.mindhub.XNHomeBanking.dto.TransactionDTO;
import com.mindhub.XNHomeBanking.repositories.AccountRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/all")
    public List<AccountDTO> getAllAccounts(){
        return accountRepositories.findAll()
                .stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toList());
    }

    @GetMapping("/accounts/{id}/transactions")
    public List<TransactionDTO> getOneAccount(@PathVariable Long id){
     return accountRepositories.findById(id)
                .map(account -> account.getTransactionSet().stream()
                      .map(TransactionDTO::new)
                      .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

    }

}//Aca termina la class AccountController.