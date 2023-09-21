package com.example.locationCar.vehicleTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.services.vehicleService.ListVehicleService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

public class ListVehicleTest {
  @Mock private VehicleRepository vehicleRepository;

  private ListVehicleService listVehicleService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    listVehicleService = new ListVehicleService(vehicleRepository);
  }

  @Test
  public void testListVehicles_Success() {
    PageRequest pageRequest = PageRequest.of(0, 20);
    Page<VehicleModel> vehiclePage = new PageImpl<>(Collections.singletonList(new VehicleModel()));
    when(vehicleRepository.findAll(pageRequest)).thenReturn(vehiclePage);

    BaseDto responseEntity = listVehicleService.listVehicles("0");

    assertEquals(HttpStatus.OK.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Veículos listados com sucesso", responseEntity.getResult().getDescription());
  }

  @Test
  public void testListVehicles_WrongPagination() {
    List<VehicleModel> vehicleList = Collections.singletonList(new VehicleModel());
    Page<VehicleModel> vehiclePage = new PageImpl<>(vehicleList);
    when(vehicleRepository.findAll(any(PageRequest.class))).thenReturn(vehiclePage);

    BaseDto responseEntity = listVehicleService.listVehicles("5");

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Página informada inválida", responseEntity.getResult().getDescription());
  }
}
