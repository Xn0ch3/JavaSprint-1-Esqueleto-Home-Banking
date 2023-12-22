package com.mindhub.XNHomeBanking.repositories;

import com.mindhub.XNHomeBanking.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LoanRepositories extends JpaRepository<Loan, Long> {
}
