package com.example.Facturation.dto;


import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class FactureDTO {
    @NotNull
    private Long clientId;

    @NotNull
    private LocalDate date;

    @NotEmpty
    private List<LigneFactureDTO> lignes;
}