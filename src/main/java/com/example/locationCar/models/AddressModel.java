package com.example.locationCar.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

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

    @OneToOne
    private ClientModel idClient;

    @OneToOne
    private EmployeeModel idEmployee;

    public ClientModel getClient() {
        return idClient;
    }

    public void setClient(ClientModel idClient) {
        this.idClient = idClient;
    }

    public EmployeeModel getEmployee() {
        return idEmployee;
    }

    public void setEmployee(EmployeeModel idEmployee) {
        this.idEmployee = idEmployee;
    }
}
