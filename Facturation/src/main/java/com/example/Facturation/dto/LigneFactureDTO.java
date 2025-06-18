package com.example.Facturation.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class LigneFactureDTO {
    @NotBlank
    private String description;

    @Min(1)
    private int quantite;

    @Positive
    private double prixUnitaireHT;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "20.0")
    private double tauxTVA;
}
