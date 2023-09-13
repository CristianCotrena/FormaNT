package com.example.locationCar.services.vehicleService;

import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SearchVehicleService {

    private final VehicleRepository vehicleRepository;

    public SearchVehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public VehicleModel searchVehicleById(UUID id) {
        Optional<VehicleModel> optionalVehicleModel = vehicleRepository.findById(id);
        if (optionalVehicleModel.isPresent()) {
            return optionalVehicleModel.get();
        } else {
            throw new IllegalArgumentException("Veículo não encontrado pelo ID informado.");
        }
    }

    public VehicleModel searchVehicleByLicense(String license) {
        Optional<VehicleModel> optionalVehicleModel = vehicleRepository.findByLicense(license);
        if (optionalVehicleModel.isPresent()){
            return optionalVehicleModel.get();
        } else {
            throw new IllegalArgumentException("Veículo não encontrado pela placa informada.");
        }
    }


}
