package com.mindhub.XNHomeBanking.dto;

import com.mindhub.XNHomeBanking.models.Account;
import com.mindhub.XNHomeBanking.models.Transaction;
import com.mindhub.XNHomeBanking.models.TransactionType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class TransactionDTO {


    private Long id;

    private TransactionType type;

    private Double amount;

    private String description;

    private LocalDateTime dateTime;

    private double accountBalance;

    //Generamos Constructor


    public TransactionDTO(Transaction transaction) {
        id = transaction.getId();
        type = transaction.getType();
        amount = transaction.getAmount();
        description = transaction.getDescription();
        dateTime = transaction.getDateTime();
        accountBalance = transaction.getAccountBalance();
    }

    //Getters de las propiedades/atributos.


    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getAccountBalance() {
        return accountBalance;
    }
}//Aca termina la clase TransactionDTO
