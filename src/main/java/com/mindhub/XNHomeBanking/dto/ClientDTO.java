package com.mindhub.XNHomeBanking.dto;

import com.mindhub.XNHomeBanking.models.Account;
import com.mindhub.XNHomeBanking.models.Card;
import com.mindhub.XNHomeBanking.models.Client;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO  {

    private Long id;

    private String firstname, lastname, email;

    private List<AccountDTO> accounts;

    private Set<ClientLoanDTO> clientLoans;

    private Set<CardDTO> card;

    //Se Genera el constructor parametrizado.

    public ClientDTO(Client client) {
        id = client.getId();
        firstname = client.getFirstname();
        lastname = client.getLastname();
        email = client.getEmail();
        accounts = client.getListAccount().stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
        clientLoans = client.getClientLoans().stream().map(clientLoan -> new ClientLoanDTO(clientLoan)).collect(Collectors.toSet());
        card = client.getCards().stream().map(card -> new CardDTO(card)).collect(Collectors.toSet());
    }

    //Se generan los Getters y Setters.

    public Set<CardDTO> getCardDTOS() {
        return card;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public Set<ClientLoanDTO> getClientLoans() {
        return clientLoans;
    }

    public void setClientLoans(Set<ClientLoanDTO> clientLoans) {
        this.clientLoans = clientLoans;
    }

    //Se Genera métodos

}//Aca termina la clase ClientDTO