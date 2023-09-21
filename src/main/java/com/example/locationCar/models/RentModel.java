package com.example.locationCar.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_RENT")
public class RentModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID idRent;

  private ZonedDateTime contractingDate;
  private ZonedDateTime returnDate;
  private Integer haveInsurance;
  private Integer status;

  @ManyToOne
  @JoinColumn(name = "clientId")
  private ClientModel client;

  @ManyToOne
  @JoinColumn(name = "employeeId")
  private EmployeeModel employee;

  @ManyToOne
  @JoinColumn(name = "vehicleId")
  private VehicleModel vehicle;

  public UUID getIdRent() {
    return idRent;
  }

  public void setIdRent(UUID idRent) {
    this.idRent = idRent;
  }

  public ZonedDateTime getContractingDate() {
    return contractingDate;
  }

  public void setContractingDate(ZonedDateTime contractingDate) {
    this.contractingDate = contractingDate;
  }

  public ZonedDateTime getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(ZonedDateTime returnDate) {
    this.returnDate = returnDate;
  }

  public Integer getHaveInsurance() {
    return haveInsurance;
  }

  public void setHaveInsurance(Integer haveInsurance) {
    this.haveInsurance = haveInsurance;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public ClientModel getClient() {
    return client;
  }

  public void setClient(ClientModel client) {
    this.client = client;
  }

  public EmployeeModel getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeModel employee) {
    this.employee = employee;
  }

  public VehicleModel getVehicle() {
    return vehicle;
  }

  public void setVehicle(VehicleModel vehicle) {
    this.vehicle = vehicle;
  }
}
