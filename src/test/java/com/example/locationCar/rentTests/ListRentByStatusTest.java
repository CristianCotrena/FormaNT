package com.example.locationCar.rentTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.mock.vehicle.VehicleMockBuilder;
import com.example.locationCar.models.RentModel;
import com.example.locationCar.models.VehicleModel;
import com.example.locationCar.repositories.RentRepository;
import com.example.locationCar.repositories.VehicleRepository;
import com.example.locationCar.services.rentService.ListRentByStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.*;

public class ListRentByStatusTest {

    @Mock
    private RentRepository rentRepository;
    @Mock
    private VehicleRepository vehicleRepository;
    @InjectMocks
    private ListRentByStatusService listRentByStatusService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        listRentByStatusService = new ListRentByStatusService(rentRepository, vehicleRepository);
    }
        VehicleModel vehicleModel = VehicleMockBuilder.build();

    @Test
    public void testListRentByStatus_Success() {
        List<RentModel> rents = new ArrayList<>();
        RentModel rent1 = new RentModel();
        rent1.setIdRent(UUID.randomUUID());
        rents.add(rent1);

        List<UUID> vehicleIds = new ArrayList<>();
        vehicleIds.add(UUID.randomUUID());

        List<VehicleModel> vehicleModels = new ArrayList<>();
        VehicleModel vehicle1 = new VehicleModel();
        vehicle1.setIdVehicle(vehicleIds.get(0));
        vehicleModels.add(vehicle1);

        Page<VehicleModel> vehicles = new PageImpl<>(vehicleModels);
        PageRequest pageRequest = PageRequest.of(1, 10);

        when(rentRepository.findByReturnDateGreaterThan(ZonedDateTime.now())).thenReturn(Optional.of(rents));
        when(vehicleRepository.findAll(pageRequest)).thenReturn(vehicles);

        BaseDto result = listRentByStatusService.listRentByStatus(0, 1);
        assertEquals(HttpStatus.OK.value(), result.getResult().getStatusCode());
        assertEquals(SuccessMessage.LIST_RENT_BY_STATUS, result.getResult().getDescription());
        assertEquals(1, ((Page<VehicleModel>) result.getData()).getContent().size());
    }

    @Test
    public void testListRentByStatus_InvalidStatus() {
        BaseDto result = listRentByStatusService.listRentByStatus(2, 1);
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResult().getStatusCode());
        List<BaseErrorDto> errors = result.getErrors();
        assertEquals(1, errors.size());
        assertEquals(ErrorMessage.INVALID_STATUS, errors.get(0).getMessage());
    }

    @Test
    public void testListRentByStatus_EmptyResult() {
        when(rentRepository.findByReturnDateGreaterThan(ZonedDateTime.now())).thenReturn(Optional.empty());
        BaseDto result = listRentByStatusService.listRentByStatus(0, 1);
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResult().getStatusCode());
        List<BaseErrorDto> errors = result.getErrors();
        assertEquals(1, errors.size());
        assertEquals(ErrorMessage.EMPTY_PAGE, errors.get(0).getMessage());
    }

    @Test
    public void testListRentByStatus_LimitCase() {
        List<RentModel> rents = Collections.emptyList();
        PageRequest pageRequest = PageRequest.of(1, 10);
        when(rentRepository.findByReturnDateGreaterThan(ZonedDateTime.now())).thenReturn(Optional.of(rents));
        when(vehicleRepository.findAll(pageRequest)).thenReturn(Page.empty());
        BaseDto result = listRentByStatusService.listRentByStatus(0, 1);
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResult().getStatusCode());
        List<BaseErrorDto> errors = result.getErrors();
        assertEquals(1, errors.size());
        assertEquals(ErrorMessage.EMPTY_PAGE, errors.get(0).getMessage());
    }
}