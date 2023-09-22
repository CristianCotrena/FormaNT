package com.example.locationCar.services.clientService;

import com.example.locationCar.dtos.ClientUpdateDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateClientService {
  private final ClientRepository clientRepository;

  public ResponseEntity<String> updateClient(UUID id, ClientUpdateDto clientUpdateDto) {
    validateUpdateClient(clientUpdateDto);

    Optional<ClientModel> clientBase = clientRepository.findById(id);

    if (clientBase.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
    }

    ClientModel clientModel = clientBase.get();

    if (clientUpdateDto.name() != null) clientModel.setName(clientUpdateDto.name());
    if (clientUpdateDto.age() != null) clientModel.setAge(clientUpdateDto.age());
    if (clientUpdateDto.cpfCnpj() != null) clientModel.setCpfCnpj(clientUpdateDto.cpfCnpj());
    if (clientUpdateDto.cnh() != null) clientModel.setCnh(clientUpdateDto.cnh());
    if (clientUpdateDto.telephone() != null) clientModel.setTelephone(clientUpdateDto.telephone());
    if (clientUpdateDto.emergencyContact() != null)
      clientModel.setEmergencyContact(clientUpdateDto.emergencyContact());

    ClientModel updatedClient = clientRepository.save(clientModel);

    return ResponseEntity.ok(updatedClient.getIdClient().toString());
  }

  public void validateUpdateClient(ClientUpdateDto clientUpdateDto) {
    if (clientUpdateDto.email() != null)
      throw new IllegalArgumentException("Não é possível alterar email.");

    if (clientUpdateDto.age() != null && (clientUpdateDto.age() < 18))
      throw new IllegalArgumentException("Idade inválida.");

    if (clientUpdateDto.telephone() != null
        && (clientUpdateDto.telephone().length() > 11 || clientUpdateDto.telephone().length() < 10))
      throw new IllegalArgumentException("Telefone inválido.");

    if (clientUpdateDto.emergencyContact() != null
        && (clientUpdateDto.emergencyContact().length() > 11
            || clientUpdateDto.emergencyContact().length() < 10))
      throw new IllegalArgumentException("Telefone de emergência inválido.");
  }
}
