package com.example.locationCar.dtos;

import com.example.locationCar.models.RentModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRentResponseDto {

    @Schema(example = "2025-03-20T13:00Z")
    private String dataContratacao;

    @Schema(example = "2025-04-09T13:00Z")
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
        @Schema(example = "Tatiana")
        private String nome;
        @Schema(example = "01630265055")
        private String cpfCnpj;
        @Schema(example = "111111111111")
        private String cnh;
        @Schema(example = "tati@gmail.com")
        private String email;
        @Schema(example = "51981220948")
        private String telefone;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmployeeInfoDto {
        @Schema(example = "Alzira")
        private String nome;
        @Schema(example = "alzira@gmail.com")
        private String email;
        @Schema(example = "5599999788")
        private String telefone;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VehicleInfoDto {
        @Schema(example = "BMW")
        private String marca;
        @Schema(example = "X5")
        private String modelo;
        @Schema(example = "NEJ9138")
        private String placa;
        @Schema(example = "150.00")
        private String valorDiaria;
    }
}