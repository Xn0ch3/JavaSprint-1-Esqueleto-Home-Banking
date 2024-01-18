package com.mindhub.XNHomeBanking.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private LocalDate creationDate;

    private Double balance;

    private boolean Active = true;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

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
    public Account(String number, LocalDate creationDate, Double balance, boolean active, AccountType accountType) {
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.Active = active;
        this.accountType = accountType;

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

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public void setTransactionSet(Set<Transaction> transactionSet) {
        this.transactionSet = transactionSet;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    //metodos de la clase
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
                "accountType=" + accountType +
                '}';
    }
}// Aca Termina la Class Account