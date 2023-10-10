package com.example.locationCar.models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_LOGINS")
public class LoginModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idLogin;

  private String password;
  private String expiresIn;

  public Long getIdLogin() {
    return idLogin;
  }

  public void setIdLogin(Long idLogin) {
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
