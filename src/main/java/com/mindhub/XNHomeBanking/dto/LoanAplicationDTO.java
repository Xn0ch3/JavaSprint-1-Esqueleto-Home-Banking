package com.mindhub.XNHomeBanking.dto;

import com.mindhub.XNHomeBanking.models.Loan;
import jakarta.persistence.ElementCollection;

import java.util.Set;

public class LoanAplicationDTO {

    private Long id;

    private Double amount;


    private Integer payments;

    private String destinyAccount;

    public LoanAplicationDTO(Long id, Double amount, Integer payments, String destinyAccount) {
        this.id = id;
        this.amount = amount;
        this.payments = payments;
        this.destinyAccount = destinyAccount;
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public String getDestinyAccount() {
        return destinyAccount;
    }
}
