package com.example.locationCar.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "TB_ADDRESS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID idAddress;

  private String publicPlace;
  private Integer number;
  private String complement;
  private String city;
  private String state;
  private String country;
  private String cep;
  private Integer status;

  @ManyToOne
  @JoinColumn(name = "clientId")
  public ClientModel client;

  @OneToOne
  @JoinColumn(name = "employeeId")
  public EmployeeModel employee;
}
