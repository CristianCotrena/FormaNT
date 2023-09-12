package com.example.locationCar.vehicleTests;
import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.input.VehicleInputDto;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.services.vehicleService.UpdateVehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UpdateVehicleTests {

    @MockBean
    private VehicleRepository vehicleRepository;

    @Autowired
    private UpdateVehicleService updateVehicleService;
    private VehicleInputDto dto;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

        dto = new VehicleInputDto();
        dto.setColor("Red");
        dto.setDailyValue(BigDecimal.valueOf(50));
        dto.setFuel("GNV");
        dto.setLicense("AFP1A20");
        dto.setBrand("Fiat");
        dto.setDoorNumber(4);
        dto.setMileage(BigDecimal.valueOf(20));
        dto.setRating(BigDecimal.valueOf(5));
        dto.setModel("UNO");

    }

    @Test
    public void updateVehicle_updateColor_success() {
        UUID vehicleId = UUID.randomUUID();
        VehicleModel existingVehicle = new VehicleModel();
        existingVehicle.setColor("Black");

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

        BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

        assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
        assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());
    }

    @Test
    public void updateVehicle_updateFuel_success() {
        UUID vehicleId = UUID.randomUUID();
        VehicleModel existingVehicle = new VehicleModel();
        existingVehicle.setFuel("Gasolina");

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

        BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

        assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
        assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());

    }

    @Test
    public void updateVehicle_updateMileage_success() {
        UUID vehicleId = UUID.randomUUID();
        VehicleModel existingVehicle = new VehicleModel();
        existingVehicle.setMileage(BigDecimal.valueOf(50));

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

        BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

        assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
        assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());

    }

    @Test
    public void updateVehicle_updateModel_success() {
        UUID vehicleId = UUID.randomUUID();
        VehicleModel existingVehicle = new VehicleModel();
        existingVehicle.setModel("UNO");

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

        BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

        assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
        assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());

    }

    @Test
    public void updateVehicle_updateLicence_success() {
        UUID vehicleId = UUID.randomUUID();
        VehicleModel existingVehicle = new VehicleModel();
        existingVehicle.setLicense("DRI1A20");

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

        BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

        assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
        assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());

    }

    @Test
    public void updateVehicle_updateRating_success() {
        UUID vehicleId = UUID.randomUUID();
        VehicleModel existingVehicle = new VehicleModel();
        existingVehicle.setRating(BigDecimal.valueOf(50));

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

        BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

        assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
        assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());

    }

    @Test
    public void updateVehicle_updateBrand_success() {
        UUID vehicleId = UUID.randomUUID();
        VehicleModel existingVehicle = new VehicleModel();
        existingVehicle.setBrand("Fiat");

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

        BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

        assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
        assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());

    }

    @Test
    public void updateVehicle_updateDoor_success() {
        UUID vehicleId = UUID.randomUUID();
        VehicleModel existingVehicle = new VehicleModel();
        existingVehicle.setDoorNumber(4);

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

        BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

        assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
        assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());

    }

    @Test
    public void updateVehicle_updateDailyValue_success() {
        UUID vehicleId = UUID.randomUUID();
        VehicleModel existingVehicle = new VehicleModel();
        existingVehicle.setDailyValue(BigDecimal.valueOf(100));

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existingVehicle));
        when(vehicleRepository.save(any(VehicleModel.class))).thenReturn(existingVehicle);

        BaseDto result = updateVehicleService.updateVehicle(vehicleId, dto);

        assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
        assertEquals("Veículo atualizado com sucesso", result.getResult().getDescription());

    }
}
