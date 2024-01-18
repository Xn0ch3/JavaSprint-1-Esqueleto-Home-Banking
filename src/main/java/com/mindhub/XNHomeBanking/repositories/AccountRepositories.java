package com.mindhub.XNHomeBanking.repositories;

import com.mindhub.XNHomeBanking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountRepositories extends JpaRepository< Account, Long> {

    boolean existsByNumber(String numberAccount);

    Account findByNumber (String number);

}
