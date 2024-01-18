package com.mindhub.XNHomeBanking.dto;

import com.mindhub.XNHomeBanking.models.Loan;
import jakarta.persistence.ElementCollection;

import java.util.Set;

public class LoanAdminDTO {


    private String name;

    private Double maxAmount;

    @ElementCollection
    private Set<Integer> payments;

    private double interestRate;

    public LoanAdminDTO(Loan loan) {
        name = loan.getName();
        maxAmount = loan.getMaxAmount();
        payments = loan.getPayments();
        interestRate = loan.getInterestRate();
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
