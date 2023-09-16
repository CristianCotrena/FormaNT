package com.example.locationCar.vehicleTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.input.VehicleInputDto;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.services.vehicleService.ListVehicleParamService;
import java.math.BigDecimal;
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

  private VehicleInputDto dto;
  private VehicleInputDto dto2;
  private VehicleInputDto dto3;

  @BeforeEach
  public void setup() {
    vehicleRepository = mock(VehicleRepository.class);
    listVehicleParamService = new ListVehicleParamService(vehicleRepository);

    dto =
        new VehicleInputDto(
            "BRA2E25",
            "Tesla",
            "s",
            4,
            "Black",
            "eletric",
            BigDecimal.valueOf(50.3),
            BigDecimal.valueOf(300.5),
            BigDecimal.valueOf(4.5));
    dto2 =
        new VehicleInputDto(
            "BRA2E22",
            "Tesla",
            "X",
            4,
            "Blue",
            "eletric",
            BigDecimal.valueOf(30.3),
            BigDecimal.valueOf(5550.5),
            BigDecimal.valueOf(3.3));
    dto2 =
        new VehicleInputDto(
            "BRA2E12",
            "Ford",
            "Fiesta",
            2,
            "Blue",
            "gasoline",
            BigDecimal.valueOf(90.3),
            BigDecimal.valueOf(50.5),
            BigDecimal.valueOf(4.8));
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
