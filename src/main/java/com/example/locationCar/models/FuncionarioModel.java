package com.example.locationCar.models;

import com.example.locationCar.models.enums.Cargo;
import com.example.locationCar.models.enums.Role;
import com.example.locationCar.models.enums.TipoContrato;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table (name= "TB_FUNCIONARIO")
public class FuncionarioModel implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID idFuncionario;
    private String nome;
    private Cargo cargo;
    @Size(min = 11, max = 14)
    private String cpfCnpj;
    private String registro;
    private String telefone;
    private TipoContrato tipoContrato;
    private Role role;
    private String email;
    private int status;

    public FuncionarioModel() {
    }

    public FuncionarioModel(UUID idFuncionario, String nome, Cargo cargo, String cpfCnpj, String registro, String telefone, TipoContrato tipoContrato, Role role, String email, int status) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cargo = cargo;
        this.cpfCnpj = cpfCnpj;
        this.registro = registro;
        this.telefone = telefone;
        this.tipoContrato = tipoContrato;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }


    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(UUID idFuncionario) {
        this.idFuncionario = idFuncionario;
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
        FuncionarioModel that = (FuncionarioModel) o;
        return status == that.status && Objects.equals(idFuncionario, that.idFuncionario) && Objects.equals(nome, that.nome) && Objects.equals(cargo, that.cargo) && Objects.equals(cpfCnpj, that.cpfCnpj) && Objects.equals(registro, that.registro) && Objects.equals(telefone, that.telefone) && Objects.equals(tipoContrato, that.tipoContrato) && Objects.equals(role, that.role) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFuncionario, nome, cargo, cpfCnpj, registro, telefone, tipoContrato, role, email, status);
    }

    @Override
    public String toString() {
        return "FuncionarioModel{" +
                "idFuncionario=" + idFuncionario +
                ", nome='" + nome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", registro='" + registro + '\'' +
                ", telefone='" + telefone + '\'' +
                ", tipoContrato='" + tipoContrato + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
