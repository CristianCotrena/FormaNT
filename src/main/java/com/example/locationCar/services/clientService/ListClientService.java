package com.example.locationCar.services.clientService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.validate.client.ListClientValidate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListClientService {
  private final ClientRepository clientRepository;

  public BaseDto listClients(String age, String page) {
    List<BaseErrorDto> errors = new ListClientValidate().validateParamsToSearch(age, page);

    if (errors.size() > 0) {
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    Integer ageToSearch = null;
    int pageToSearch = 0;

    if (age != null) ageToSearch = Integer.parseInt(age);
    if (page != null) pageToSearch = Integer.parseInt(page);

    PageRequest pageRequest = PageRequest.of(pageToSearch, 20);
    Page<ClientModel> clients = clientRepository.listByAge(ageToSearch, pageRequest);

    if (clients.isEmpty()) {
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.NOT_FOUND);
      return result.get();
    }

    if ((clients.getTotalPages() - 1) < pageToSearch) {
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_PAGE);
      return result.get();
    }

    return new ResponseSuccessBuilder<>(HttpStatus.OK, clients, SuccessMessage.LIST_CLIENTS).get();
  }
}
