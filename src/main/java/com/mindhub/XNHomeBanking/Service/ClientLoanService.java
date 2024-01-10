package com.mindhub.XNHomeBanking.Service;

import com.mindhub.XNHomeBanking.models.ClientLoan;
import org.springframework.beans.factory.annotation.Autowired;

public interface ClientLoanService {

    @Autowired

    void saveClientLoan(ClientLoan clientLoan);
}
