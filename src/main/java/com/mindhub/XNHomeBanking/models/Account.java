package com.mindhub.XNHomeBanking.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private LocalDate creationDate;

    private Double balance;

    //Creamos la Relación Uno-A-Muchos y la promesa de los datos con Fecht tipo EAGER para traer todos los datos.
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    //Creamos la lista de tipo Set (HashSet Construye un conjunto nuevo y vacío;)
    private Set<Transaction> transactionSet = new HashSet<>();

    //Relaciones  Account/Client
    @ManyToOne
    private Client client;

    //Constructor Vacío de Account.
    public Account() {
    }
    //Constructor Parametrizado Account
    public Account(String number, LocalDate creationDate, Double balance) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
    }
    //Getters y Setters del Constructor Parametrizado.
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

    public Set<Transaction> getTransactionSet() {
        return transactionSet;
    }

    public void setTransactionSet(Set<Transaction> transactionSet) {
        this.transactionSet = transactionSet;
    }

    public void addTransaction(Transaction transaction){
        transaction.setAccount(this);
        this.transactionSet.add(transaction);
    }

    //ToString para imprimir los datos en consola.
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