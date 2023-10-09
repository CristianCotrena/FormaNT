package com.example.locationCar.rentTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.dtos.RentUpdateDto;
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

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    updateRentService = new UpdateRentService(rentRepository);
  }

  @Test
  public void testUpdateRent_Success() {
    UUID rentId = UUID.randomUUID();
    RentModel rent = new RentModel();
    RentUpdateDto rentUpdateDto = new RentUpdateDto();
    rent.setIdRent(rentId);

    try {
      ZonedDateTime contractingDate = ZonedDateTime.parse("2023-12-25T03:00:00Z");
      ZonedDateTime returnDate = ZonedDateTime.parse("2024-01-11T03:00:00Z");
      ZonedDateTime returnDateDto = ZonedDateTime.parse("2024-01-15T03:00:00Z");

      rent.setContractingDate(contractingDate);
      rent.setReturnDate(returnDate);
      rent.setHaveInsurance(1);

      rentUpdateDto.setContractingDate(contractingDate);
      rentUpdateDto.setReturnDate(returnDateDto);
      rentUpdateDto.setHaveInsurance(1);

      when(rentRepository.findById(rentId)).thenReturn(Optional.of(rent));
      when(rentRepository.save(rent)).thenReturn(rent);

      BaseDto result = updateRentService.updateRent(rentId.toString(), rentUpdateDto);

      assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
      assertEquals("Aluguel atualizado com sucesso", result.getResult().getDescription());
    } catch (Exception e) {
    }
  }

  @Test
  public void testUpdateRent_InvalidReturnDate() {
    UUID rentId = UUID.randomUUID();
    RentModel rent = new RentModel();
    RentUpdateDto rentUpdateDto = new RentUpdateDto();
    rent.setIdRent(rentId);

    try {
      ZonedDateTime contractingDate = ZonedDateTime.parse("2023-12-25T03:00:00Z");
      ZonedDateTime returnDate = ZonedDateTime.parse("2024-01-11T03:00:00Z");

      rent.setContractingDate(contractingDate);
      rent.setReturnDate(returnDate);
      rent.setHaveInsurance(1);

      rentUpdateDto.setContractingDate(contractingDate);
      rentUpdateDto.setReturnDate(returnDate);
      rentUpdateDto.setHaveInsurance(1);

      when(rentRepository.findById(rentId)).thenReturn(Optional.of(rent));
      when(rentRepository.save(rent)).thenReturn(rent);

      BaseDto result = updateRentService.updateRent(rentId.toString(), rentUpdateDto);

      assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
      assertEquals("Bad Request", result.getResult().getDescription());
    } catch (Exception e) {
    }
  }

  @Test
  public void testUpdateRent_NotFound() {
    UUID rentId = UUID.randomUUID();
    when(rentRepository.findById(rentId)).thenReturn(Optional.empty());
    RentUpdateDto rentUpdateDto = new RentUpdateDto();

    try {
      ZonedDateTime contractingDateDto = ZonedDateTime.parse("2023-12-25T03:00:00Z");
      ZonedDateTime returnDateDto = ZonedDateTime.parse("2024-01-11T03:00:00Z");

      rentUpdateDto.setContractingDate(contractingDateDto);
      rentUpdateDto.setReturnDate(returnDateDto);
      rentUpdateDto.setHaveInsurance(1);

      BaseDto result = updateRentService.updateRent(rentId.toString(), rentUpdateDto);

      assertEquals(HttpStatus.NOT_FOUND.value(), result.getResult().getStatusCode());
      assertEquals("Não encontrado", result.getResult().getDescription());
    } catch (Exception e) {
    }
  }
}
