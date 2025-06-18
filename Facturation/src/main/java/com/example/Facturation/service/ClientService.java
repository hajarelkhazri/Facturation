package com.example.Facturation.service;




import java.util.List;

import com.example.Facturation.dto.ClientDto;
import com.example.Facturation.model.Client;

public interface ClientService {
    Client createClient(ClientDto clientDto);
    List<Client> getAllClients();
    Client getClientById(Long id);
}