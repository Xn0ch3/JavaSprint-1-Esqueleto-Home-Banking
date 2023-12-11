package com.mindhub.XNHomeBanking.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private LocalDate creationDate;

    private Double balance;

@ManyToOne
    private Client client;

    public Account() {
    }

    public Account(String number, LocalDate creationDate, Double balance) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    //No lleva SET Id xq lo maneja la base de datos. Solo queremos obtenerlo.

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", creationDate=" + creationDate +
                ", balance=" + balance +
                ", client=" + client +
                '}';
    }
}// Aca Termina la Class Account