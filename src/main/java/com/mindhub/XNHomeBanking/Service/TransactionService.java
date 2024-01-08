package com.mindhub.XNHomeBanking.Service;

import com.mindhub.XNHomeBanking.models.Transaction;

public interface TransactionService {
    void saveTransaction(Transaction transactionDebit);
}
