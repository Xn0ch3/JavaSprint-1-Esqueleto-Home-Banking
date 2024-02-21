package com.mindhub.XNHomeBanking.service;

import com.mindhub.XNHomeBanking.models.Transaction;

public interface TransactionService {
    void saveTransaction(Transaction transactionDebit);
}
