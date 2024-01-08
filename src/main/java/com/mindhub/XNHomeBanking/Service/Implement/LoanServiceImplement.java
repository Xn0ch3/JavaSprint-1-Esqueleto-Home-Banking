package com.mindhub.XNHomeBanking.Service.Implement;

import com.mindhub.XNHomeBanking.Service.LoanService;
import com.mindhub.XNHomeBanking.dto.LoanDTO;
import com.mindhub.XNHomeBanking.models.Loan;
import com.mindhub.XNHomeBanking.repositories.LoanRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImplement implements LoanService {

    @Autowired
    private LoanRepositories loanRepositories;



    @Override
    public List<Loan> getAllLoan() {
        return loanRepositories.findAll();
    }

    @Override
    public List<LoanDTO> getAllLoanDTO() {
        return getAllLoan().stream().map(loan -> new LoanDTO(loan)).collect(Collectors.toList());
    }

    @Override
    public Loan findById(Long id) {
        return loanRepositories.findById(id).orElse(null);
    }
}