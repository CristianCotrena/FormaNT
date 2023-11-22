package com.example.locationCar.dtos;

import com.example.locationCar.models.ClientModel;
import com.example.locationCar.models.EmployeeModel;
import com.example.locationCar.models.VehicleModel;
import com.fasterxml.jackson.annotation.JsonInclude;

public class SearchRentRequestDto {

  private String dataContratacao;
  private String dataDevolucao;
  private boolean possuiSeguro;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private ClientModel clientModel;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private EmployeeModel employeeModel;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private VehicleModel vehicleModel;

  public SearchRentRequestDto() {}

  public String getDataContratacao() {
    return dataContratacao;
  }

  public void setDataContratacao(String dataContratacao) {
    this.dataContratacao = dataContratacao;
  }

  public String getDataDevolucao() {
    return dataDevolucao;
  }

  public void setDataDevolucao(String dataDevolucao) {
    this.dataDevolucao = dataDevolucao;
  }

  public boolean isPossuiSeguro() {
    return possuiSeguro;
  }

  public void setPossuiSeguro(boolean possuiSeguro) {
    this.possuiSeguro = possuiSeguro;
  }

  public ClientModel getCliente() {
    return clientModel;
  }

  public void setCliente(ClientModel cliente) {
    this.clientModel = cliente;
  }

  public EmployeeModel getFuncionarios() {
    return employeeModel;
  }

  public void setFuncionarios(EmployeeModel funcionarios) {
    this.employeeModel = funcionarios;
  }

  public VehicleModel getVeiculo() {
    return vehicleModel;
  }

  public void setVeiculo(VehicleModel veiculo) {
    this.vehicleModel = veiculo;
  }
}
