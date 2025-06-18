package com.example.Facturation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Facturation.model.LigneFacture;
/**
 * Repository pour les lignes de facture.
 * Ce repository est souvent utilisé en cascade via la facture elle-même.
 */
@Repository
public interface LigneFactureRepository extends JpaRepository<LigneFacture, Long> {
    // Pas de méthode personnalisée nécessaire pour le moment
}
