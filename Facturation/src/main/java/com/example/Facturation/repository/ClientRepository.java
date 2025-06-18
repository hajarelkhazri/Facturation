package com.example.Facturation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Facturation.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
