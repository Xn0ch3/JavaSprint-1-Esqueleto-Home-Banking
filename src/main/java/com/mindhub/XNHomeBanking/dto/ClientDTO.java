package com.mindhub.XNHomeBanking.dto;

import com.mindhub.XNHomeBanking.models.Account;
import com.mindhub.XNHomeBanking.models.Client;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO  {

        private Long id;


        private String firstname, lastname, email;

        private List<AccountDTO> listAccount;


    public ClientDTO(Client client) {
        id = client.getId();

        firstname = client.getFirstname();

        lastname = client.getLastname();

        email = client.getEmail();

        listAccount = client.getListAccount()
                .stream().map(account -> new AccountDTO(account))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public List<AccountDTO> getListAccount() {
        return listAccount;
    }
}//Aca termina la clase ClientDTO
