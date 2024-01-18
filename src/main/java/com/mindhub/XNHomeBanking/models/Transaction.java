package com.mindhub.XNHomeBanking.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private double amount;

    private String description;

    private LocalDateTime dateTime;
    @ManyToOne
    private Account account;

    private double accountBalance;

    //Constructor Vacío.
    public Transaction() {
    }

    //Constructor Parametrizado
    public Transaction(TransactionType type, double amount, String description, LocalDateTime dateTime, double accountBalance) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.dateTime = dateTime;
        this.accountBalance = accountBalance;
    }
    //Getters y Setters del Constructor Transaction, (Se elimina el SetID solo necesitamos el Get).
    public Long getId() {
        return id;
    }


    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    //ToString para poder imprimir los datos y no el espacio en Memoria.
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", account=" + account +
                ", accountBalance" + accountBalance +
                '}';
    }

}//Aca termina class Transaction