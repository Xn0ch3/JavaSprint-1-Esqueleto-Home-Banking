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

@RestController
@RequestMapping("/api")
public class AccountController {

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