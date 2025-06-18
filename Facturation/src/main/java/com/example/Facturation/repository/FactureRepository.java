package com.example.Facturation.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Facturation.model.Facture;


/**
 * Repository pour les factures, permettant la recherche par client ou date.
 */
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

    // Bonus : rechercher les factures d’un client
    List<Facture> findByClientId(Long clientId);

    // Bonus : rechercher les factures par date
    List<Facture> findByDate(LocalDate date);
}
