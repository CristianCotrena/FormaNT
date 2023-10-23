package com.example.locationCar.dtos;

import com.example.locationCar.models.RentModel;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRentResponseDto {
    private String dataContratacao;
    private String dataDevolucao;
    private boolean possuiSeguro;
    private ClientInfoDto client;
    private EmployeeInfoDto employee;
    private VehicleInfoDto vehicle;

    public SearchRentResponseDto(RentModel rentModel) {
        this.dataContratacao = rentModel.getContractingDate().toString();
        this.dataDevolucao = rentModel.getReturnDate().toString();
        this.possuiSeguro = rentModel.getHaveInsurance() == 1;

        this.client = ClientInfoDto.builder()
                .nome(rentModel.getClient().getName())
                .cpfCnpj(rentModel.getClient().getCpfCnpj())
                .cnh(rentModel.getClient().getCnh())
                .email(rentModel.getClient().getEmail())
                .telefone(rentModel.getClient().getTelephone())
                .build();

        this.employee = EmployeeInfoDto.builder()
                .nome(rentModel.getEmployee().getName())
                .email(rentModel.getEmployee().getEmail())
                .telefone(rentModel.getEmployee().getPhone())
                .build();

        this.vehicle = VehicleInfoDto.builder()
                .marca(rentModel.getVehicle().getBrand())
                .modelo(rentModel.getVehicle().getModel())
                .placa(rentModel.getVehicle().getLicense())
                .valorDiaria(String.valueOf(rentModel.getVehicle().getDailyValue()))
                .build();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClientInfoDto {
        private String nome;
        private String cpfCnpj;
        private String cnh;
        private String email;
        private String telefone;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmployeeInfoDto {
        private String nome;
        private String email;
        private String telefone;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VehicleInfoDto {
        private String marca;
        private String modelo;
        private String placa;
        private String valorDiaria;
    }
}