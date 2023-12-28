package com.mindhub.XNHomeBanking.repositories;

import com.mindhub.XNHomeBanking.models.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountRepositories extends JpaRepository< Account, Long> {

    boolean existsBynumber(String numberAccount);

}
