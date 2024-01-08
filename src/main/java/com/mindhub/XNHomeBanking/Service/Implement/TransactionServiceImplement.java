package com.mindhub.XNHomeBanking.Service.Implement;

import com.mindhub.XNHomeBanking.Service.TransactionService;
import com.mindhub.XNHomeBanking.models.Transaction;
import com.mindhub.XNHomeBanking.repositories.TransactionRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImplement implements TransactionService {
    @Autowired
    private TransactionRepositories transactionRepositories;

    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepositories.save(transaction);
    }
}
