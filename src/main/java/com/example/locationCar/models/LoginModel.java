package com.example.locationCar.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "TB_LOGINS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID idLogin;

  private String email;
  private String password;
  private String expiresIn;
}
