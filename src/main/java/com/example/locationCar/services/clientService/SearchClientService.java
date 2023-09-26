package com.example.locationCar.services.clientService;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchClientService {

  private static ClientRepository clientRepository;

  public ClientModel findUserByEmail(String Email) {
    ClientModel client = clientRepository.findByEmail(Email).get();
    if (client != null) {
      return client;
    } else {
      throw new IllegalArgumentException("Client not found");
    }
  }

  public ClientModel findUserByCpfCnpj(String CpfCnpj) {
    ClientModel client = clientRepository.findByCpfCnpj(CpfCnpj).get();
    if (client != null) {
      return client;
    } else {
      throw new IllegalArgumentException("Client not found");
    }
  }

  public ClientModel findUserById(UUID id) {
    Optional<ClientModel> client = clientRepository.findById(id);
    if (client.isPresent()) {
      return client.get();
    } else {
      throw new IllegalArgumentException("Client not found");
    }
  }
}
