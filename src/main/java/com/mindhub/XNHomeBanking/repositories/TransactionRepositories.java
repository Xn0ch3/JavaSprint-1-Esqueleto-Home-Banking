package com.mindhub.XNHomeBanking.repositories;

import com.mindhub.XNHomeBanking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionRepositories  extends JpaRepository<Transaction, Long> {

}
