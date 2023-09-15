package com.example.locationCar.dtos;

import com.example.locationCar.models.AddressModel;
import java.util.UUID;

public class SearchAddressRecordDto {

    private UUID idAddress;

    private String publicPlace;
    private int number;
    private String complement;
    private String city;
    private String state;
    private String country;
    private String cep;

    public SearchAddressRecordDto() {
    }

    public SearchAddressRecordDto(AddressModel addressModel) {
        this.idAddress = addressModel.getIdAddress();
        this.publicPlace = addressModel.getPublicPlace();
        this.number = addressModel.getNumber();
        this.complement = addressModel.getComplement();
        this.city = addressModel.getCity();
        this.state = addressModel.getState();
        this.country = addressModel.getCountry();
        this.cep = addressModel.getCep();
    }
}