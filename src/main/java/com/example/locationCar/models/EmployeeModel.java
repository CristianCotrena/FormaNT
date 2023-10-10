package com.example.locationCar.models;

import com.example.locationCar.models.enums.ContractType;
import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "TB_EMPLOYEE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Schema(hidden = true)
  private UUID employeeId;

  @Schema(example = "Marineide")
  private String name;

  @Enumerated(EnumType.STRING)
  private Position position;

  @Schema(example = "01630265053")
  private String cpfCnpj;

  @Schema(example = "1212")
  private String registry;

  @Schema(example = "(51) 3335-0435")
  private String phone;

  @Enumerated(EnumType.STRING)
  @Schema(example = "CLT")
  private ContractType contractType;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Schema(example = "marineide@gmail.com")
  private String email;

  @Schema(hidden = true)
  private int status = 1;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EmployeeModel that = (EmployeeModel) o;
    return status == that.status
        && Objects.equals(employeeId, that.employeeId)
        && Objects.equals(name, that.name)
        && Objects.equals(position, that.position)
        && Objects.equals(cpfCnpj, that.cpfCnpj)
        && Objects.equals(registry, that.registry)
        && Objects.equals(phone, that.phone)
        && Objects.equals(contractType, that.contractType)
        && Objects.equals(role, that.role)
        && Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        employeeId, name, position, cpfCnpj, registry, phone, contractType, role, email, status);
  }

  @Override
  public String toString() {
    return "EmployeeModel{"
        + "employeeId="
        + employeeId
        + ", name='"
        + name
        + '\''
        + ", position='"
        + position
        + '\''
        + ", cpfCnpj='"
        + cpfCnpj
        + '\''
        + ", registry='"
        + registry
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", contractType='"
        + contractType
        + '\''
        + ", role='"
        + role
        + '\''
        + ", email='"
        + email
        + '\''
        + ", status="
        + status
        + '}';
  }
}
