package com.example.locationCar.clientTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.services.clientService.ListClientService;
import com.example.locationCar.validate.client.ListClientValidate;
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

public class ListClientTest {

  @Mock private ClientRepository clientRepository;

  @Mock private ListClientValidate listClientValidate;

  private ListClientService listClientService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    listClientService = new ListClientService(clientRepository);
  }

  @Test
  public void testListClients_Success() {
    Page<ClientModel> clientPage = new PageImpl<>(Collections.singletonList(new ClientModel()));
    when(clientRepository.listByAge(any(), any())).thenReturn(clientPage);

    BaseDto responseEntity = listClientService.listClients("37", "0");

    assertEquals(HttpStatus.OK.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Clientes listados com sucesso", responseEntity.getResult().getDescription());
  }

  @Test
  public void testListClients_InvalidData() {
    List<BaseErrorDto> errors =
        Collections.singletonList(new BaseErrorDto("age", "Campo inválido"));
    when(listClientValidate.validateParamsToSearch(anyString(), anyString())).thenReturn(errors);

    BaseDto responseEntity = listClientService.listClients("ERRADO", "0");

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Bad Request", responseEntity.getResult().getDescription());
  }

  @Test
  public void testListClients_WrongPagination() {
    List<ClientModel> clientList = Collections.singletonList(new ClientModel());
    Page<ClientModel> clientPage = new PageImpl<>(clientList, PageRequest.of(0, 2), 1);
    when(clientRepository.listByAge(any(), any())).thenReturn(clientPage);

    BaseDto responseEntity = listClientService.listClients("20", "5");

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getResult().getStatusCode());
    assertEquals("Página informada inválida", responseEntity.getResult().getDescription());
  }
}
