package com.mindhub.XNHomeBanking.service;

import com.mindhub.XNHomeBanking.models.ClientLoan;
import org.springframework.beans.factory.annotation.Autowired;

public interface ClientLoanService {

    @Autowired

    void saveClientLoan(ClientLoan clientLoan);
}
