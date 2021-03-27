package br.com.midhatdrops.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.repository.UserRepository;
import br.com.midhatdrops.utils.exceptions.IdNotFoundException;

public class NewUserForm implements Form {
  private String name;
  @NotBlank
  @NotEmpty
  private String adress;
  @NotBlank
  @NotEmpty
  private String password;
  @NotBlank
  @NotEmpty
  private String username;

  public NewUserForm(String name, String adress, String password, String username) {
    this.name = name;
    this.adress = adress;
    this.password = password;
    this.username = username;
  }

  public String getName() {
    return this.name;
  }

  public String getAdress() {
    return this.adress;
  }

  public String getPassword() {
    return this.password;
  }

  public String getUsername() {
    return this.username;
  }

  @Override
  public User convert(UserRepository repository) {
    String encodePassword = new BCryptPasswordEncoder().encode(this.password);
    return new User(this.name, this.adress, this.username, encodePassword, new BigDecimal("1000"));
  }

  @Override
  public Transaction convert(TransactionsRepository repository) throws IdNotFoundException {
    return null;
  }

}
