package com.mindhub.XNHomeBanking.Service.Implement;

import com.mindhub.XNHomeBanking.Service.ClientLoanService;
import com.mindhub.XNHomeBanking.models.ClientLoan;
import com.mindhub.XNHomeBanking.repositories.ClientLoanRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientLoanServiceImplement implements ClientLoanService {

    @Autowired
    private ClientLoanRepositories clientLoanRepositories;
    @Override
    public void saveClientLoan(ClientLoan clientLoan) {
        clientLoanRepositories.save(clientLoan);
    }
}
