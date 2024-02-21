package com.mindhub.XNHomeBanking.service;

import com.mindhub.XNHomeBanking.dto.LoanDTO;
import com.mindhub.XNHomeBanking.models.Loan;

import java.util.List;

public interface LoanService{

    void saveLoanAd(Loan loan);
    List<Loan> getAllLoan();

    List<LoanDTO> getAllLoanDTO();

    Loan findById(Long id);
}
