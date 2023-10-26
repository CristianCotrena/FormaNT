package com.example.locationCar.rentTests;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.mock.rent.RentMockBuilder;
import com.example.locationCar.mock.vehicle.VehicleMockBuilder;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.RentRepository;
import com.example.locationCar.services.rentService.SearchRentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SearchRentTest {
    @Mock
    private RentRepository rentRepository;
    @InjectMocks
    private SearchRentService searchRentService;
    private RentModel rentModel;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        rentModel = RentMockBuilder.build();
        VehicleModel vehicleModel = VehicleMockBuilder.build();
        rentModel.setVehicle(vehicleModel);
    }

    @Test
    public void testSearchRentSuccess() {
        when(rentRepository.findById(rentModel.getIdRent())).thenReturn(Optional.of(rentModel));

        BaseDto result = searchRentService.searchRent(rentModel.getIdRent().toString());

        Integer expectedStatusCode = HttpStatus.OK.value();
        Integer actualStatusCode = result.getResult().getStatusCode();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void testSearchRentFailure() {
        when(rentRepository.existsById(rentModel.getIdRent())).thenReturn(false);

        BaseDto result = searchRentService.searchRent(String.valueOf(false));

        Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        Integer actualStatusCode = result.getResult().getStatusCode();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    public void testSearchRentNoParam() {
        when(rentRepository.findById(null)).thenReturn(Optional.of(rentModel));

        BaseDto result = searchRentService.searchRent(null);

        Integer expectedStatusCode = HttpStatus.BAD_REQUEST.value();
        Integer actualStatusCode = result.getResult().getStatusCode();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

}
