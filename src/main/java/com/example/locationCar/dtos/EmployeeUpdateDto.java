package com.example.locationCar.dtos;

import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;

public class EmployeeUpdateDto {

  @Schema(example = "Joao Pedro")
  String name;

  @Schema(example = "VENDEDOR")
  String position;

  @Schema(example = "44190639800")
  String cpfCnpj;

  @Schema(example = "1212")
  String registry;

  @Schema(example = "(51) 9999-9999")
  String phone;

  @Schema(example = "CLT")
  String contractType;

  @Schema(example = "VENDEDOR")
  String role;

  @Schema(example = "joaopedro@teste.com")
  String email;

  @Schema(example = "1")
  Integer status;

  public EmployeeUpdateDto(
      String newName,
      Position position,
      String number,
      String number1,
      String s,
      ContractType contractType,
      Role role,
      String mail,
      Integer status) {}

  public EmployeeUpdateDto() {}

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getCpfCnpj() {
    return cpfCnpj;
  }

  public void setCpfCnpj(String cpfCnpj) {
    this.cpfCnpj = cpfCnpj;
  }

  public String getRegistry() {
    return registry;
  }

  public void setRegistry(String registry) {
    this.registry = registry;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getContractType() {
    return contractType;
  }

  public void setContractType(String contractType) {
    this.contractType = contractType;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "EmployeeDto{"
        + "name='"
        + name
        + '\''
        + ", position="
        + position
        + ", cpfCnpj='"
        + cpfCnpj
        + '\''
        + ", registry='"
        + registry
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", contractType="
        + contractType
        + ", role="
        + role
        + ", email='"
        + email
        + '\''
        + '}';
  }
}
