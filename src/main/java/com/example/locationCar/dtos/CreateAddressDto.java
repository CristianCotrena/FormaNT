package com.example.locationCar.dtos;

public class CreateAddressDto {
    private String addressId;

    public CreateAddressDto(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
