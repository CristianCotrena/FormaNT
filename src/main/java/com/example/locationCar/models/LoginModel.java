package com.example.locationCar.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_LOGINS")
public class LoginModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID idLogin;

  private String password;
  private String expiresIn;

  public UUID getIdLogin() {
    return idLogin;
  }

  public void setIdLogin(UUID idLogin) {
    this.idLogin = idLogin;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(String expiresIn) {
    this.expiresIn = expiresIn;
  }
}
