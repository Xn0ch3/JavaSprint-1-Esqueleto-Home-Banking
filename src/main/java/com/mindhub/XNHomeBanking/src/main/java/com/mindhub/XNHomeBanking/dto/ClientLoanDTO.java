package com.mindhub.XNHomeBanking.dto;


import com.mindhub.XNHomeBanking.models.ClientLoan;
import com.mindhub.XNHomeBanking.models.Loan;

import java.util.List;

public class ClientLoanDTO {

    private Long id;
    private Long loan;
    private String name;
    private double amount;
    private int payment;

    //Se generan constructor Vacío.

    public ClientLoanDTO(ClientLoan clientLoan) {
        id = clientLoan.getId();
        loan = clientLoan.getLoan().getId();
        name = clientLoan.getLoan().getName();
        amount = clientLoan.getAmount();
        payment = clientLoan.getClientLoanPayments();
    }

    //Getters & Setters


    public Long getId() {
        return id;
    }

    public Long getLoan() {
        return loan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
}//Aca termina la clase ClientDTO