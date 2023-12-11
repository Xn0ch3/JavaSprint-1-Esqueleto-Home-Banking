package com.mindhub.XNHomeBanking.dto;

import com.mindhub.XNHomeBanking.models.Account;

import java.time.LocalDate;

public class AccountDTO {

    private Long id;

    private String number;

    private LocalDate creationDate;

    private Double balance;

    public AccountDTO() {
    }

    public AccountDTO(Account account){

        id = account.getId();

        number = account.getNumber();

        creationDate = account.getCreationDate();

        balance = account.getBalance();
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Double getBalance() {
        return balance;
    }
}//Aca termina la Clase AccountDTO
