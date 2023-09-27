package com.example.locationCar.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
}
