package com.example.locationCar.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "TB_RENT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
