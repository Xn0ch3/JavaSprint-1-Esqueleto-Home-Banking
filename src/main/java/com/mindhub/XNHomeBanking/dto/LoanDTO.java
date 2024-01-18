package com.mindhub.XNHomeBanking.dto;

import com.mindhub.XNHomeBanking.models.Loan;
import jakarta.persistence.ElementCollection;

import java.util.Set;

public class LoanDTO {

    private Long id;

    private String name;

    private Double maxAmount;

    @ElementCollection
    private Set<Integer> payments;

    private double interestRate;

    public LoanDTO(Loan loan) {
        id = loan.getId();
        name = loan.getName();
        maxAmount = loan.getMaxAmount();
        payments = loan.getPayments();
        interestRate = loan.getInterestRate();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public Set<Integer> getPayments() {
        return payments;
    }

    public double getInterestRate() {
        return interestRate;
    }
}