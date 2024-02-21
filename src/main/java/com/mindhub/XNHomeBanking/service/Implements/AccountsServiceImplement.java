package com.mindhub.XNHomeBanking.service.Implements;

import com.mindhub.XNHomeBanking.service.AccountService;
import com.mindhub.XNHomeBanking.dto.AccountDTO;
import com.mindhub.XNHomeBanking.dto.TransactionDTO;
import com.mindhub.XNHomeBanking.models.Account;
import com.mindhub.XNHomeBanking.repositories.AccountRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AccountsServiceImplement implements AccountService {
    @Autowired
    private AccountRepositories accountRepositories;


    @Override
    public Account findByNumber(String number) {
        return accountRepositories.findByNumber(number);
    }

    @Override
    public Account findByIdAccount(long id) {
        return accountRepositories.findById(id).orElse(null);
    }

    @Override
    public List<AccountDTO> getAllAccountsDTO() {
        return getAllAccount().stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepositories.findAll();
    }

    @Override
    public List<TransactionDTO> findById(long id) {
        return accountRepositories.findById(id)
                .map(account -> account.getTransactionSet()
                        .stream()
                        .map(transaction -> new TransactionDTO(transaction))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public boolean existsByNumber(String numberAccount) {
        return accountRepositories.existsByNumber(numberAccount);
    }

    @Override
    public void saveAccount(Account account) {
         accountRepositories.save(account);
    }

    @Override
    public void deleteAccount(Account account) {
        Account accounts = accountRepositories.findById(account.getId()).orElse(null);
        accounts.setActive(false);
        accountRepositories.save(accounts);
    }


}
