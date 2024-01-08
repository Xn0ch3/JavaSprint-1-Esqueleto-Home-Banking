package com.mindhub.XNHomeBanking.Service;

import com.mindhub.XNHomeBanking.dto.LoanDTO;
import com.mindhub.XNHomeBanking.models.Loan;

import java.util.List;

public interface LoanService{


    List<Loan> getAllLoan();

    List<LoanDTO> getAllLoanDTO();

    Loan findById(Long id);
}
