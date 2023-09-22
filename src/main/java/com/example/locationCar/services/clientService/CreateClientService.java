package com.example.locationCar.services.clientService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.CreateClientDto;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.validate.client.CreateClientValidate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateClientService {

  private ClientRepository clientRepository;

  public BaseDto createClient(ClientModel clientModel) {
    List<BaseErrorDto> errors = new CreateClientValidate().validate(clientModel);
    if (errors.size() > 0) {
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    if (!clientRepository.findByEmail(clientModel.getEmail()).isEmpty()) {
      errors.add(new BaseErrorDto("email", ErrorMessage.UNIQUE_FIELD));
    }

    if (!clientRepository.findByCpfCnpj(clientModel.getCpfCnpj()).isEmpty()) {
      errors.add(new BaseErrorDto("cpfCnpj", ErrorMessage.UNIQUE_FIELD));
    }

    if (errors.size() > 0) {
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    UUID createdId = clientRepository.save(clientModel).getIdClient();
    return new ResponseSuccessBuilder<CreateClientDto>(
            HttpStatus.CREATED,
            new CreateClientDto(createdId.toString()),
            SuccessMessage.CREATE_CLIENT)
        .get();
  }
}
