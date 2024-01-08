package com.mindhub.XNHomeBanking.Service;

import com.mindhub.XNHomeBanking.dto.AccountDTO;
import com.mindhub.XNHomeBanking.dto.TransactionDTO;
import com.mindhub.XNHomeBanking.models.Account;

import java.util.List;

public interface AccountService {

    Account findByNumber(String number);

    List<AccountDTO> getAllAccountsDTO();

    List<Account> getAllAccount();

    List<TransactionDTO> findById(long id);

    boolean existsByNumber(String numberAccount);

    void saveAccount(Account account);
}
