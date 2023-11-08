package com.example.locationCar.rentTests;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseResultDto;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.RentRepository;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.services.RentService.ListRentByIdService;
import com.example.locationCar.services.vehicleService.ListVehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ListRentTest {
    @Mock
    private RentRepository rentRepository;

    private ListRentByIdService listRentByIdService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        listRentByIdService = new ListRentByIdService(rentRepository);
    }

    @Test
    public void testListRent_GetRentsByClientId_success() {
        UUID clientId = UUID.randomUUID();
        List<RentModel> rentList = new ArrayList<>();
        rentList.add(new RentModel());
        Mockito.when(rentRepository.findByClient_IdClient(clientId)).thenReturn(rentList);

        BaseDto result = listRentByIdService.getRentsByClientId(clientId);

        assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
        assertEquals(SuccessMessage.LIST_RENT, result.getResult().getDescription());
    }

    @Test
    public void testListRent_GetRentsByEmployeeId_success() {
        UUID employeeId = UUID.randomUUID();
        List<RentModel> rentList = new ArrayList<>();
        rentList.add(new RentModel());
        Mockito.when(rentRepository.findByEmployee_EmployeeId(employeeId)).thenReturn(rentList);

        BaseDto result = listRentByIdService.getRentsByEmployeeId(employeeId);

        assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
        assertEquals(SuccessMessage.LIST_RENT, result.getResult().getDescription());
    }

}
