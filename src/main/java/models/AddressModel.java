package models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

        @Entity
        @Table(name = "TB_ADDRESS")
        public class AddressModel implements Serializable {
            private static final long serialVersionUID = 1L;

            @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            private UUID idAddress;
//
//            @OneToOne
//            @JoinColumn(name = "clientId", referencedColumnName = "idClient")
//            private ClientModel client;       //criando conexão com clientId
//            @OneToOne
//            @JoinColumn(name = "employeeId", referencedColumnName = "idemployee")
//            private EmployeeModel employee;     //criando conexão com employeeId

            private String publicPlace;
            private int number;
            private String complement;
            private String city;
            private String state;
            private String country;
            private String cep;
            private int status;


            public UUID getIdAddress() {
                return idAddress;
            }

            public void setIdAddress(UUID idAddress) {
                this.idAddress = idAddress;
            }

            public String getPublicPlace() {
                return publicPlace;
            }

            public void setPublicPlace(String publicPlace) {
                this.publicPlace = publicPlace;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getComplement() {
                return complement;
            }

            public void setComplement(String complement) {
                this.complement = complement;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getCep() {
                return cep;
            }

            public void setCep(String cep) {
                this.cep = cep;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }