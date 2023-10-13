package com.example.locationCar.services.core;

import com.example.locationCar.models.LoginModel;
import com.example.locationCar.repositories.LoginRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EncryptService {

  private LoginRepository loginRepository;

  public String encrypt(String password) {
    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

    return hashedPassword;
  }

  public Boolean compare(String password, String email) {
    Optional<LoginModel> user = loginRepository.findByEmail(email);

    if (user.isPresent()) {
      Boolean isValid = BCrypt.checkpw(password, user.get().getPassword());

      return isValid;
    } else {
      return false;
    }
  }
}
