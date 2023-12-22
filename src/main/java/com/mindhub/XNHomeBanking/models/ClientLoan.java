package com.mindhub.XNHomeBanking.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClientLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private Integer clientLoanPayments;

    //Relaciones De ClientLoan.

    @ManyToOne
    private Loan loan;

    @ManyToOne
    private Client client;

    //Constructor Vació.

    public ClientLoan() {
    }

    //Constructor Parametrizado.

    public ClientLoan(double amount, Integer clientLoanPayments) {
        this.amount = amount;
        this.clientLoanPayments = clientLoanPayments;
    }

    //Getters y Setters.

    public Long getId() {
        return id;
    }

    //No vamos a ocupar SetId.


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getClientLoanPayments() {
        return clientLoanPayments;
    }

    public void setClientLoanPayments(Integer clientLoanPayments) {
        this.clientLoanPayments = clientLoanPayments;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "ClientLoan{" +
                "id=" + id +
                ", amount=" + amount +
                ", clientLoanPayments=" + clientLoanPayments +
                ", loan=" + loan +
                ", client=" + client +
                '}';
    }
}//Aca termina la clase de ClientLoan
