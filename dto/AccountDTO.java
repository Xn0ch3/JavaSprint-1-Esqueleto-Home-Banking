package com.mindhub.XNHomeBanking.dto;

import com.mindhub.XNHomeBanking.models.Account;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDTO {

    private long id;

    private String number;

    private LocalDate creationDate;

    private Double balance;

    private List<TransactionDTO> transaction;

    public AccountDTO() {
    }

    public AccountDTO(Account account){

        this.id = account.getId();

        this.number = account.getNumber();

        this.creationDate = account.getCreationDate();

        this.balance = account.getBalance();

        this.transaction = account.getTransactionSet().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public List<TransactionDTO> getTransaction() {
        return transaction;
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