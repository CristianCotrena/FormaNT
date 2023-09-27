package com.example.locationCar.services.addressService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.client.CorreiosBuscaCepClient;
import com.example.locationCar.client.ResponseViaCep;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.CreateAddressDto;
import com.example.locationCar.dtos.input.AddressInputDto;
import com.example.locationCar.models.AddressModel;
import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.repositories.AddressRepository;
import com.example.locationCar.repositories.ClientRepository;
import com.example.locationCar.repositories.EmployeeRepository;
import com.example.locationCar.validate.address.CreateAddressValidate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAddressService {

  private AddressRepository addressRepository;

  private EmployeeRepository employeeRepository;

  private ClientRepository clientRepository;

  public BaseDto inserir(AddressInputDto dto) {
    List<BaseErrorDto> errors = new CreateAddressValidate().validate(dto);

    if (errors.size() > 0) {
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    AddressModel address = new AddressModel();

    if (dto.getIdClient() != null) {
      Optional<ClientModel> client = clientRepository.findById(UUID.fromString(dto.getIdClient()));
      if (client.isEmpty()) {
        errors.add(new BaseErrorDto("idClient", ErrorMessage.CLIENT_DOESNT_EXIST));
        ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
        return result.get();
      }

      Optional<AddressModel> entity = addressRepository.findByClient(client.get());

      if (!entity.isEmpty()) {
        errors.add(new BaseErrorDto("idClient", ErrorMessage.CLIENT_ALREADY_HAS_AN_ADDRESS));
        ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
        return result.get();
      }

      address.setClient(client.get());
    }

    if (dto.getIdEmployee() != null) {
      Optional<EmployeeModel> employee =
          employeeRepository.findById(UUID.fromString(dto.getIdEmployee()));
      if (employee.isEmpty()) {
        errors.add(new BaseErrorDto("idEmployee", ErrorMessage.EMPLOYEE_DOESNT_EXIST));
        ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
        return result.get();
      }

      Optional<AddressModel> entity = addressRepository.findByEmployee(employee.get());

      if (!entity.isEmpty()) {
        errors.add(new BaseErrorDto("idEmployee", ErrorMessage.EMPLOYEE_ALREADY_HAS_AN_ADDRESS));
        ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
        return result.get();
      }

      address.setEmployee(employee.get());
    }

    address.setCep(dto.getCep());
    address.setComplement(dto.getComplement());
    address.setNumber(dto.getNumber());

    ResponseViaCep responseViaCep = CorreiosBuscaCepClient.getAddressInformation(dto.getCep());
    if (responseViaCep.getErro() == null) {
      address.setCity(responseViaCep.getLocalidade());
      address.setCountry("Brasil");
      address.setState(responseViaCep.getUf());
      address.setPublicPlace(responseViaCep.getLogradouro());
      address.setStatus(1);
    } else {
      errors.add(new BaseErrorDto("cep", ErrorMessage.NOT_FOUND));
      ResponseErrorBuilder result = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, errors);
      return result.get();
    }

    UUID createdId = addressRepository.save(address).getIdAddress();

    return new ResponseSuccessBuilder<CreateAddressDto>(
            HttpStatus.CREATED,
            new CreateAddressDto(createdId.toString()),
            SuccessMessage.CREATE_ADDRESS)
        .get();
  }
}
