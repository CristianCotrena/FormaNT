package com.example.locationCar.models;

import com.example.locationCar.models.enums.Position;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.models.enums.ContractType;
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

    private UUID employeeId;
    private String name;
    private Position position;
    private String cpfCnpj;
    private String registry;
    private String phone;
    private ContractType contractType;
    private Role role;
    private String email;
    private int status;

    public EmployeeModel() {
    }

    public EmployeeModel(UUID idFuncionario, String nome, Position cargo, String cpfCnpj, String registro, String telefone, ContractType tipoContrato, Role role, String email, int status) {
        this.employeeId = idFuncionario;
        this.name = nome;
        this.position = cargo;
        this.cpfCnpj = cpfCnpj;
        this.registry = registro;
        this.phone = telefone;
        this.contractType = tipoContrato;
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
        return "FuncionarioModel{" +
                "idFuncionario=" + employeeId +
                ", nome='" + name + '\'' +
                ", cargo='" + position + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", registro='" + registry + '\'' +
                ", telefone='" + phone + '\'' +
                ", tipoContrato='" + contractType + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}