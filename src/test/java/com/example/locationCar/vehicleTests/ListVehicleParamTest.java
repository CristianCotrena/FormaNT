package com.example.locationCar.vehicleTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.services.vehicleService.ListVehicleParamService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public class ListVehicleParamTest {

  @MockBean private VehicleRepository vehicleRepository;

  @Autowired private ListVehicleParamService listVehicleParamService;

  @BeforeEach
  public void setup() {
    vehicleRepository = mock(VehicleRepository.class);
    listVehicleParamService = new ListVehicleParamService(vehicleRepository);
  }

  @Test
  public void testListVehicles_Success() {
    List<VehicleModel> vehicles = new ArrayList<>();
    vehicles.add(new VehicleModel());
    vehicles.add(new VehicleModel());
    vehicles.add(new VehicleModel());
    Pageable pageable = PageRequest.of(0, 3);
    Page<VehicleModel> vehiclePage = new PageImpl<>(vehicles, pageable, 3);
    when(vehicleRepository.findAll(any(Specification.class), any(Pageable.class)))
        .thenReturn(vehiclePage);

    BaseDto dto = listVehicleParamService.listVehicles(pageable, null, null, null, null);

    assertEquals("Veículos listados com sucesso", dto.getResult().getDescription());
    assertEquals(200, dto.getResult().getStatusCode());
  }

  @Test
  public void testListVehicles_Error() {
    List<VehicleModel> vehicles = new ArrayList<>();
    Pageable pageable = PageRequest.of(0, 3);
    Page<VehicleModel> vehiclePage = new PageImpl<>(vehicles, pageable, 3);
    when(vehicleRepository.findAll(any(Specification.class), any(Pageable.class)))
        .thenReturn(vehiclePage);

    BaseDto dto = listVehicleParamService.listVehicles(pageable, null, null, null, null);

    assertEquals("Not Found", dto.getResult().getDescription());
    assertEquals(404, dto.getResult().getStatusCode());
  }
}
