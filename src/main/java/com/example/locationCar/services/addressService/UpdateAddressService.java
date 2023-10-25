package com.example.locationCar.services.addressService;

import com.example.locationCar.base.dto.BaseDto;
import com.example.locationCar.base.dto.BaseErrorDto;
import com.example.locationCar.builder.ResponseErrorBuilder;
import com.example.locationCar.builder.ResponseSuccessBuilder;
import com.example.locationCar.client.CorreiosBuscaCepClient;
import com.example.locationCar.client.ResponseViaCep;
import com.example.locationCar.constants.ErrorMessage;
import com.example.locationCar.constants.SuccessMessage;
import com.example.locationCar.dtos.AddressUpdateDto;
import com.example.locationCar.models.AddressModel;
import com.example.locationCar.repositories.AddressRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateAddressService {
    private final AddressRepository addressRepository;

    public UpdateAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public BaseDto updateAddress(UUID idAddress, AddressUpdateDto addressUpdateDto) {
        Optional<AddressModel> addressBase = addressRepository.findById(idAddress);

        if (addressBase.isEmpty()) {
            List<BaseErrorDto> notFoundErrors = List.of(new BaseErrorDto("Address", ErrorMessage.NOT_FOUND));
            return new ResponseErrorBuilder(HttpStatus.NOT_FOUND, notFoundErrors).get();
        }

        AddressModel addressModel = addressBase.get();
        try {
            List<BaseErrorDto> badRequestList = new ArrayList<>();

            if (addressUpdateDto.getCep() == null) {
                badRequestList.add(new BaseErrorDto("cep", ErrorMessage.EMPTY_FIELD));
            }
            if (addressUpdateDto.getCity() != null) {
                badRequestList.add(new BaseErrorDto("city", ErrorMessage.CEP_ERROR));
            }
            if (addressUpdateDto.getState() != null) {
                badRequestList.add(new BaseErrorDto("state", ErrorMessage.CEP_ERROR));
            }
            if (addressUpdateDto.getCountry() != null) {
                badRequestList.add(new BaseErrorDto("country", ErrorMessage.CEP_ERROR));
            }
            if (!badRequestList.isEmpty()) {
                return new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, badRequestList).get();
            }

            ResponseViaCep responseViaCep = CorreiosBuscaCepClient.getAddressInformation(addressUpdateDto.getCep());
            if (addressUpdateDto.getRoad() == null) {
                if (!responseViaCep.getLogradouro().equals("")) {
                    addressModel.setRoad(responseViaCep.getLogradouro());
                }
            } else {
                addressModel.setRoad(addressUpdateDto.getRoad());
            }
            addressModel.setCity(responseViaCep.getLocalidade());

            addressModel.setState(responseViaCep.getUf());

            addressModel.setCountry("Brasil");
            addressModel.setCep(addressUpdateDto.getCep());

        } catch (HttpClientErrorException e) {
            List<BaseErrorDto> notFoundErrors = List.of(new BaseErrorDto("cep", ErrorMessage.NOT_FOUND));
            return new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, notFoundErrors).get();
        }

        if (addressUpdateDto.getPublicPlace() != null) {
            addressModel.setPublicPlace(addressUpdateDto.getPublicPlace());
        }
        if (addressUpdateDto.getNumber() != null) {
            addressModel.setNumber(addressUpdateDto.getNumber());
        }
        if (addressUpdateDto.getComplement() != null) {
            addressModel.setComplement(addressUpdateDto.getComplement());
        }

        addressRepository.save(addressModel);

        return new ResponseSuccessBuilder<AddressUpdateDto>(HttpStatus.OK, new AddressUpdateDto(idAddress.toString()),
                SuccessMessage.UPDATE_ADDRESS).get();
    }
}
