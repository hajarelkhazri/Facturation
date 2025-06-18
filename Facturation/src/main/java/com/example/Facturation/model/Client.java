package com.example.Facturation.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Client {
    @Id 
    @GeneratedValue 

    private Long id;

    private String nom;

    private String email;

    private String siret;
    
    private LocalDate dateCreation;
}

