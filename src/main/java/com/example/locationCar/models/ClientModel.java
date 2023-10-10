package com.example.locationCar.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "TB_CLIENTS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Schema(hidden = true)
  private UUID idClient;

  @Schema(example = "Carlitos Tevez")
  private String name;

  @Schema(example = "43576334222")
  private String cpfCnpj;

  @Schema(example = "874658935467")
  private String cnh;

  @Schema(example = "23")
  private Integer age;

  @Schema(example = "11999999999")
  private String telephone;

  @Schema(example = "11999999998")
  private String emergencyContact;

  @Schema(example = "carlitosboca@gmail.com")
  private String email;

  @Schema(hidden = true)
  private Integer status = 1;
}
