package com.mindhub.XNHomeBanking.models;

import jakarta.annotation.Generated;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
    public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double maxAmount;

    @ElementCollection
    private Set<Integer> payments;

    private double interestRate;

//Relaciones

   @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
   private Set<ClientLoan> clientLoans = new HashSet<>();

//Constructor Vacío.

    public Loan() {
    }
//Constructor Parametrizado.

    public Loan(String name, Double maxAmount, Set<Integer> payments, double interestRate) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
        this.interestRate = interestRate;
    }


//Getters y setters


    public Long getId() {
        return id;
    }

    //Set Id no tiene que estar, no queremos modificar Id.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Set<Integer> getPayments() {
        return payments;
    }

    public void setPayments(Set<Integer> payments) {
        this.payments = payments;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    //Métodos para agregar ClientLoan(clientes) a Loan(Prestamos)
    public void addClientLoan(ClientLoan clientLoan){
        clientLoan.setLoan(this);
        this.clientLoans.add(clientLoan);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxAmount=" + maxAmount +
                ", payments=" + payments +
                ", interestRate" + interestRate +
                '}';
    }
}//Aca termina la clase Loan.
