package com.example.locationCar.rentTests;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.repositories.RentRepository;
import com.example.locationCar.services.rentService.DeleteRentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteRentTest {

  @MockBean private RentRepository rentRepository;

  @Autowired private DeleteRentService deleteRentService;

  private Long idRent;

  @BeforeEach
  public void setup() {
    rentRepository = mock(RentRepository.class);
    deleteRentService = new DeleteRentService(rentRepository);

    idRent = 1L;
  }

  @Test
  public void testCreateRent_Success() {

    when(rentRepository.findById(any(Long.class))).thenReturn(Optional.of(new RentModel()));

    BaseDto responseEntity = deleteRentService.remove(idRent);

    assertEquals(HttpStatus.OK.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Deletado com sucesso.", responseEntity.getResult().getDescription());
  }

  @Test
  public void testCreateVehicle_RentDoesntxist_Error() {
    when(rentRepository.findById(any(Long.class))).thenReturn(Optional.empty());

    BaseDto responseEntity = deleteRentService.remove(idRent);

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Bad Request", responseEntity.getResult().getDescription());
  }
}
