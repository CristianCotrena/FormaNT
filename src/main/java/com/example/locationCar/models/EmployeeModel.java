package com.example.locationCar.models;

import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.models.enums.ContractType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_FUNCIONARIO")
public class EmployeeModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema (hidden = true)
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
    private ContractType contractType;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Schema(example = "marineide@gmail.com")
    private String email;

    @Schema (hidden = true)
    private int status = 1;

    public EmployeeModel() {
    }

    public EmployeeModel(UUID idEmployee, String name, Position position, String cpfCnpj, String registry, String phone, ContractType contractType, Role role, String email, int status) {
        this.employeeId = idEmployee;
        this.name = name;
        this.position = position;
        this.cpfCnpj = cpfCnpj;
        this.registry = registry;
        this.phone = phone;
        this.contractType = contractType;
        this.role = role;
        this.email = email;
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
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

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeModel that = (EmployeeModel) o;
        return status == that.status && Objects.equals(employeeId, that.employeeId) && Objects.equals(name, that.name) && Objects.equals(position, that.position) && Objects.equals(cpfCnpj, that.cpfCnpj) && Objects.equals(registry, that.registry) && Objects.equals(phone, that.phone) && Objects.equals(contractType, that.contractType) && Objects.equals(role, that.role) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, name, position, cpfCnpj, registry, phone, contractType, role, email, status);
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", registry='" + registry + '\'' +
                ", phone='" + phone + '\'' +
                ", contractType='" + contractType + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
