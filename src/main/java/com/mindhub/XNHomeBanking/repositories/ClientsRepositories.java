package com.mindhub.XNHomeBanking.repositories;

import com.mindhub.XNHomeBanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientsRepositories extends JpaRepository<Client , Long> {

}
