package com.example.locationCar.services.clientService;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ListClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ListClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Page<ClientModel> listClients(Integer age, Integer page){
        // Verificação idade maior ou igual a 18
        if(age != null && age < 18){
            throw new IllegalArgumentException("Idade informada precisa ser maior ou igual a 18.");
        }

        int pageToSearch = 0;

        if(page == null) pageToSearch = 0;

        PageRequest pageRequest = PageRequest.of(pageToSearch, 20);

        return clientRepository.listByAgeGreaterThan(age, pageRequest);
    }
}
