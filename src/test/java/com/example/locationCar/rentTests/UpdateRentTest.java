package com.example.locationCar.rentTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.RentUpdateDto;
import com.example.locationCar.mock.rent.RentMockBuilder;
import com.example.locationCar.mock.rent.RentUpdateDtoMockBuilder;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.repositories.RentRepository;
import com.example.locationCar.services.rentService.UpdateRentService;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class UpdateRentTest {
  @Mock private RentRepository rentRepository;

  @InjectMocks private UpdateRentService updateRentService;

  private RentModel rentModel;
  private RentUpdateDto rentUpdateDto;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    updateRentService = new UpdateRentService(rentRepository);

    rentModel = RentMockBuilder.build();
    rentUpdateDto = RentUpdateDtoMockBuilder.build();
  }

  @Test
  public void testUpdateRent_Success() {
    when(rentRepository.findById(rentModel.getIdRent())).thenReturn(Optional.of(rentModel));
    when(rentRepository.save(rentModel)).thenReturn(rentModel);

    BaseDto result = updateRentService.updateRent(rentModel.getIdRent().toString(), rentUpdateDto);

    assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
    assertEquals("Aluguel atualizado com sucesso", result.getResult().getDescription());
  }

  @Test
  public void testUpdateRent_InvalidReturnDate() {
    rentUpdateDto.setReturnDate(ZonedDateTime.parse("2024-01-11T03:00:00Z"));

    when(rentRepository.findById(rentModel.getIdRent())).thenReturn(Optional.of(rentModel));
    when(rentRepository.save(rentModel)).thenReturn(rentModel);

    BaseDto result = updateRentService.updateRent(rentModel.getIdRent().toString(), rentUpdateDto);

    assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
    assertEquals("Bad Request", result.getResult().getDescription());
  }

  @Test
  public void testUpdateRent_NotFound() {
    UUID rentId = UUID.randomUUID();
    when(rentRepository.findById(rentId)).thenReturn(Optional.empty());

    BaseDto result = updateRentService.updateRent(rentId.toString(), rentUpdateDto);

    assertEquals(HttpStatus.NOT_FOUND.value(), result.getResult().getStatusCode());
    assertEquals("Não encontrado", result.getResult().getDescription());
  }
}
