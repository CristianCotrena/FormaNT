package com.example.locationCar.dtos;

public class DeleteVehicleDto {

    private boolean status;

    public DeleteVehicleDto(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
    private boolean status;

    public DeleteVehicleDto(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
     **/
}
