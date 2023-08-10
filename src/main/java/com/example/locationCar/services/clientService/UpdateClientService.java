package com.example.locationCar.services.clientService;

import com.example.locationCar.dtos.ClientUpdateDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateClientService {
    private final ClientRepository clientRepository;

    public UpdateClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity<String> updateClient(UUID id, ClientUpdateDto clientUpdateDto) {
        validateUpdateClient(clientUpdateDto);

        Optional<ClientModel> clientBase = clientRepository.findById(id);

        if (clientBase.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        ClientModel clientModel = clientBase.get();

        if (clientUpdateDto.name() != null) clientModel.setName(clientUpdateDto.name());
        if (clientUpdateDto.age() != null) clientModel.setAge(clientUpdateDto.age());
        if (clientUpdateDto.cpfCnpj() != null) clientModel.setAge(clientUpdateDto.age());
        if (clientUpdateDto.cnh() != null) clientModel.setAge(clientUpdateDto.age());
        if (clientUpdateDto.telephone() != null) clientModel.setAge(clientUpdateDto.age());
        if (clientUpdateDto.emergencyContact() != null) clientModel.setAge(clientUpdateDto.age());

        ClientModel updatedClient = clientRepository.save(clientModel);

        return ResponseEntity.ok(updatedClient.getIdClient().toString());
    }

    public void validateUpdateClient(ClientUpdateDto clientUpdateDto){
        if(clientUpdateDto.age() != null && (clientUpdateDto.age() < 18))
            throw new IllegalArgumentException("Idade inválida.");

        if(clientUpdateDto.telephone() != null && (clientUpdateDto.telephone().length() > 13 || clientUpdateDto.telephone().length() < 12))
            throw new IllegalArgumentException("Telefone inválido.");

        if(clientUpdateDto.emergencyContact() != null && (clientUpdateDto.emergencyContact().length() > 13 || clientUpdateDto.emergencyContact().length() < 12))
            throw new IllegalArgumentException("Telefone de emergência inválido.");
    }
}