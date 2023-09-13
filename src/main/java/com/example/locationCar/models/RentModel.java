package com.example.locationCar.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "TB_RENT")
public class RentModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID idRent;

  private Date contractingDate;
  private Date returnDate;
  private Integer haveInsurance;
  private String statusVehicle;

  @ManyToOne
  @JoinColumn(name = "clientId")
  private ClientModel idClient;

  @ManyToOne
  @JoinColumn(name = "employeeId")
  private EmployeeModel employeeId;

  @ManyToOne
  @JoinColumn(name = "vehicleId")
  private VehicleModel idVehicle;

  public UUID getIdRent() {
    return idRent;
  }

  public void setIdRent(UUID idRent) {
    this.idRent = idRent;
  }

  public Date getContractingDate() {
    return contractingDate;
  }

  public void setContractingDate(Date contractingDate) {
    this.contractingDate = contractingDate;
  }

  public Date getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(Date returnDate) {
    this.returnDate = returnDate;
  }

  public Integer getHaveInsurance() {
    return haveInsurance;
  }

  public void setHaveInsurance(Integer haveInsurance) {
    this.haveInsurance = haveInsurance;
  }

  public String getStatusVehicle() {
    return statusVehicle;
  }

  public void setStatusVehicle(String statusVehicle) {
    this.statusVehicle = statusVehicle;
  }
}
