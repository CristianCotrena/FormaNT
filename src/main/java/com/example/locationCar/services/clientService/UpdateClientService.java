package com.example.locationCar.services.clientService;


import com.example.locationCar.dtos.ClientRecordDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.services.clientService.utils.ClientRules;

import java.util.Optional;
import java.util.UUID;

public class UpdateClientService {

    ClientRules clientRules;
    ClientModel clientModel;
    ClientRepository clientRepository;

    public UpdateClientService(ClientModel clientModel, ClientRepository clientRepository, ClientRules clientRules) {
        this.clientModel = clientModel;
        this.clientRepository = clientRepository;
        this.clientRules = clientRules;
    }

    public ClientModel updateClient(Optional<ClientModel> opitionalClientModel, ClientRecordDto clientRecordDto) {
        if (!clientRules.isPhoneValid(clientRecordDto.telephone()) && !clientRules.isPhoneValid(clientRecordDto.emergencyContact()) && !clientRules.isCPFValid(clientRecordDto.cpfCnpj()) && !clientRules.isCNPJValid(clientRecordDto.cpfCnpj()) && !clientRules.isCnhValid(clientRecordDto.cnh()) && !clientRules.isValidAge(clientRecordDto.age())) {
            throw new IllegalArgumentException("Dados inválidos.");
        } else {
            var clientModel = new ClientModel(clientRecordDto.name(), clientRecordDto.cpfCnpj(), clientRecordDto.cnh(), clientRecordDto.age(), clientRecordDto.telephone(), clientRecordDto.emergencyContact(), clientRecordDto.email());
            clientRepository.save(clientModel);
            return clientModel;
        }
    }

//    public ClientModel updateClientEmergencyContact(String emergencyContact) {
//        if (!clientRules.isPhoneValid(emergencyContact)) {
//            throw new IllegalArgumentException("Telefone inválido.");
//        } else {
//            clientModel.setEmergencyContact(emergencyContact);
//            clientRepository.save(clientModel);
//            return clientModel;
//        }
//    }
//
//    public ClientModel updateClientEmailError(String email) {
//        if (!email.equals(clientModel.getEmail())) {
//            throw new IllegalArgumentException("Email não pode ser alterado.");
//        }
//        return clientModel;
//    }
//
//
//    public ClientModel updateClientCpfCnpj(String cpfCnpj) {
//        if (cpfCnpj != null) {
//            if (!clientRules.isCPFValid(cpfCnpj)) {
//                throw new IllegalArgumentException("CPF inválido.");
//            } else if (!clientRules.isCNPJValid(cpfCnpj)) {
//                throw new IllegalArgumentException("CNPJ inválido.");
//            }
//        } else {
//            throw new IllegalArgumentException("CPF/CNPJ inválido.");
//        }
//        clientModel.setCpfCnpj(cpfCnpj);
//        return clientModel;
//    }
//
//    public ClientModel updateClientCnh(String cnh) {
//        if (!clientRules.isCnhValid(cnh)) {
//            throw new IllegalArgumentException("CNH inválida.");
//        } else {
//            clientModel.setCnh(cnh);
//            clientRepository.save(clientModel);
//            return clientModel;
//        }
//    }
//
//    public ClientModel updateName(String name) {
//            clientModel.setName(name);
//            clientRepository.save(clientModel);
//            return clientModel;
//    }
//
//    public ClientModel updateClientAge(int age) {
//        if (!clientRules.isValidAge(age)) {
//            throw new IllegalArgumentException("Somente maiores de 18 anos podem alugar um carro.");
//        } else {
//            clientModel.setAge(age);
//            clientRepository.save(clientModel);
//            return clientModel;
//        }
//    }
}
