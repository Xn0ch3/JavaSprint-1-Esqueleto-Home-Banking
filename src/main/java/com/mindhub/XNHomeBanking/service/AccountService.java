package com.mindhub.XNHomeBanking.service;

import com.mindhub.XNHomeBanking.dto.AccountDTO;
import com.mindhub.XNHomeBanking.dto.TransactionDTO;
import com.mindhub.XNHomeBanking.models.Account;

import java.util.List;

public interface AccountService {

    Account findByNumber(String number);

    Account findByIdAccount(long id);

    List<AccountDTO> getAllAccountsDTO();

    List<Account> getAllAccount();

    List<TransactionDTO> findById(long id);

    boolean existsByNumber(String numberAccount);

    void saveAccount(Account account);

    void deleteAccount(Account account);
}
