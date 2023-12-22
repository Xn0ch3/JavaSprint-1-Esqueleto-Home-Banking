package com.mindhub.XNHomeBanking.repositories;

import com.mindhub.XNHomeBanking.models.Client;
import com.mindhub.XNHomeBanking.models.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientLoanRepositories extends JpaRepository<ClientLoan , Long> {
}//
