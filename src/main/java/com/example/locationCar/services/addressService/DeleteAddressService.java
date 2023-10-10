package com.example.locationCar.services.addressService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.DeleteAddressDto;
import com.example.locationCar.models.AddressModel;
import com.example.locationCar.repositories.AddressRepository;
import com.example.locationCar.validate.address.DeleteAddressValidate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DeleteAddressService {
  private final AddressRepository addressRepository;

  public DeleteAddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  public BaseDto deleteAddress(String idAddress, String idEmployee, String idClient) {
    List<BaseErrorDto> errors =
        new DeleteAddressValidate().validateParams(idAddress, idEmployee, idClient);

    if (errors.size() > 0) {
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(
              HttpStatus.BAD_REQUEST, ErrorMessage.ONE_MANDATORY_FIELD, errors);
      return result.get();
    }

    if (new DeleteAddressValidate().validateUUID(idAddress, idEmployee, idClient) == false) {
      ResponseErrorBuilder result =
          new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, ErrorMessage.INVALID_UUID);
      return result.get();
    }

    String idToSearch = idToSearch(idAddress, idEmployee, idClient);
    Optional<AddressModel> address = null;

    if (idToSearch.equals("idAddress")) {
      address = addressRepository.findById(UUID.fromString(idAddress));
    } else if (idToSearch.equals("idEmployee")) {
      address = addressRepository.findByEmployeeEmployeeId(UUID.fromString(idEmployee));
    } else if (idToSearch.equals("idClient")) {
      address = addressRepository.findByClientIdClient(UUID.fromString(idClient));
    }

    if (address.isPresent()) {
      AddressModel addressModel = address.get();
      addressModel.setStatus(0);
      AddressModel addressUpdated = addressRepository.save(addressModel);

      return new ResponseSuccessBuilder<>(
              HttpStatus.OK,
              new DeleteAddressDto(addressUpdated.getIdAddress(), addressUpdated.getStatus()),
              SuccessMessage.DELETE_SUCCESS)
          .get();
    }

    List<BaseErrorDto> idNotFound = new ArrayList<>();

    idNotFound.add(new BaseErrorDto("id", ErrorMessage.NOT_FOUND));
    ResponseErrorBuilder idNotFoundError =
        new ResponseErrorBuilder(HttpStatus.NOT_FOUND, idNotFound);
    return idNotFoundError.get();
  }

  public String idToSearch(String idAddress, String idEmployee, String idClient) {
    if (!idAddress.isEmpty()) {
      return "idAddress";
    } else if (!idEmployee.isEmpty()) {
      return "idEmployee";
    } else if (!idClient.isEmpty()) {
      return "idClient";
    }

    return null;
  }
}
