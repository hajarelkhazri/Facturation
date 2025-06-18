package com.example.Facturation.service.impl;



import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Facturation.dto.FactureDTO;
import com.example.Facturation.model.Client;
import com.example.Facturation.model.Facture;
import com.example.Facturation.model.LigneFacture;
import com.example.Facturation.repository.ClientRepository;
import com.example.Facturation.repository.FactureRepository;
import com.example.Facturation.repository.LigneFactureRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class FactureServiceImpl implements FactureService {

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LigneFactureRepository ligneFactureRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Facture createFacture(FactureDTO factureDto) {
        Client client = clientRepository.findById(factureDto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));

        Facture facture = new Facture();
        facture.setClient(client);
        facture.setDate(factureDto.getDate());

        List<LigneFacture> lignes = factureDto.getLignes().stream().map(dto -> {
            LigneFacture ligne = new LigneFacture();
            ligne.setDescription(dto.getDescription());
            ligne.setQuantite(dto.getQuantite());
            ligne.setPrixUnitaireHT(dto.getPrixUnitaireHT());
            ligne.setTauxTVA(dto.getTauxTVA());
            ligne.setFacture(facture);
            return ligne;
        }).collect(Collectors.toList());

        double totalHT = lignes.stream()
                .mapToDouble(l -> l.getPrixUnitaireHT() * l.getQuantite())
                .sum();

        double totalTVA = lignes.stream()
                .mapToDouble(l -> l.getPrixUnitaireHT() * l.getQuantite() * (l.getTauxTVA() / 100))
                .sum();

        facture.setTotalHT(totalHT);
        facture.setTotalTVA(totalTVA);
        facture.setTotalTTC(totalHT + totalTVA);
        facture.setLignes(lignes);

        Facture saved = factureRepository.save(facture);
        ligneFactureRepository.saveAll(lignes);

        return saved;
    }

    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    public Facture getFactureById(Long id) {
        return factureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture non trouvée"));
    }

    
    public String exportFactureAsJson(Long id) {
        Facture facture = getFactureById(id);
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(facture);
        } catch (Exception e) {
            throw new RuntimeException("Erreur export JSON", e);
        }
    }

    public List<Facture> findFacturesByClientId(Long clientId) {
        return factureRepository.findByClientId(clientId);
    }


    public List<Facture> findFacturesByDate(LocalDate date) {
        return factureRepository.findByDate(date);
    }
}

