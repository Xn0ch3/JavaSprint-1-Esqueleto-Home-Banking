package com.mindhub.XNHomeBanking.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TransactionType type;

    private Double amount;

    private String description;

    private LocalDateTime dateTime;
    @ManyToOne
    private Account account;

    //Constructor Vacío.
    public Transaction() {
    }

    //Constructor Parametrizado
    public Transaction(TransactionType type, Double amount, String description, LocalDateTime dateTime) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.dateTime = dateTime;
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
                '}';
    }

}//Aca termina class Transaction