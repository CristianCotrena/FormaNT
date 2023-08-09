package com.example.locationCar.services.clientService;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.services.clientService.utils.ClientRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateClientService {

    ClientRepository clientRepository;

    public CreateClientService(ClientRepository clientRepository, ClientRules clientRules) {


    public static boolean isEmailValid(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean isCnhValid(String cnh) {
        return cnh.matches("^[0-9]{10,12}$"); // CNH com 12 dígitos. O padrão é 11. Adicionamos um digito a mais.
    }

    public static boolean isCPFValid(String cpf) {
        return cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$") || cpf.matches("^\\d{11}$"); // CPF com 11 dígitos.
    }

    public static boolean isCNPJValid(String cnpj) {
        return cnpj.matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$") || cnpj.matches("^\\d{14}$"); // CNPJ com 14 dígitos.
    }

    public static boolean isPhoneValid(String phone) {
        return phone.matches("^\\d{2}\\d{4,5}-\\d{4}$") || phone.matches("^\\d{10,11}$");
    }

    public UUID createClient(ClientModel clientModel) {
        String cpfCnpj = clientModel.getCpfCnpj();
        String cleanedCpfCnpj = cpfCnpj.replaceAll("[^0-9]", "");
        String getEmail = clientModel.getEmail();
        String getCnh = clientModel.getCnh();
        String getTelephone = clientModel.getTelephone();
        String getEmergencyContact = clientModel.getEmergencyContact();
        ClientModel existingClient = clientRepository.findByEmailAndCpfCnpj(getEmail, cpfCnpj);

        // Verificação de Email existente.
        if (clientRepository.findByEmail(getEmail) != null){
            throw new IllegalArgumentException("E-mail já cadastrado para outro cliente.");
        }

        // Verificação de CPF/CNPJ existente.
        if (clientRepository.findByCpfCnpj(cpfCnpj) != null){
            throw new IllegalArgumentException("CPF/CNPJ já cadastrado para outro cliente.");
        }

        // Validação de CPF/CNPJ
        if (cpfCnpj != null && cpfCnpj.length() > 0) {
            if (cleanedCpfCnpj.length() == 11) {
                if (!isCPFValid(clientModel.getCpfCnpj())) {
                    throw new IllegalArgumentException("CPF inválido.");
                }
            } else if (cleanedCpfCnpj.length() == 14) {
                if (!isCNPJValid(clientModel.getCpfCnpj())) {
                    throw new IllegalArgumentException("CNPJ inválido.");
                }
            } else {
                throw new IllegalArgumentException("CPF/CNPJ inválido.");
            }
        }

        // Verificações de Email e CPF/CNPJ existentes simultaneamente.
        if (existingClient != null){
            throw new IllegalArgumentException("E-mail e CPF/CNPJ já cadastrados para outro cliente.");
        }


        // Validações de email, cnh e telefone
        if(!isEmailValid(getEmail)){
            throw new IllegalArgumentException("E-mail inválido.");
        }

        if(!isCnhValid(getCnh)){
            throw new IllegalArgumentException("CNH inválida.");
        }

        if(!isPhoneValid(getTelephone)){
            throw new IllegalArgumentException("Telefone inválido.");
        }

        if(!isPhoneValid(getEmergencyContact)){
            throw new IllegalArgumentException("Telefone do contato de emergência inválido.");
        }

        return clientRepository.save(clientModel).getIdClient();
    }
}
