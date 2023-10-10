package com.example.locationCar.services.clientService;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DeleteClientService {
  ClientRepository clientRepository;

  public DeleteClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public Optional<ClientModel> getClient(Long id) {
    return clientRepository.findById(id);
  }

  public void deleteClient(Long id) {
    Optional<ClientModel> clientOptional = clientRepository.findById(id);

    if (clientOptional.isPresent()) {
      ClientModel statusClient = clientOptional.get();
      statusClient.setStatus(0);
      clientRepository.save(statusClient);
    }
  }
}
