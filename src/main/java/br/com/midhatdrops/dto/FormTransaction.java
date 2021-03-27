package br.com.midhatdrops.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.UserRepository;
import br.com.midhatdrops.utils.exceptions.IdNotFoundException;

public class FormTransaction {

  @DateTimeFormat
  private LocalDateTime date;
  @Min(value = 0, message = "Number must be positive ")
  private BigDecimal value;
  @Min(value = 0)
  private Long userId;
  @NotBlank
  @NotEmpty
  private String adress;

  @Deprecated
  public FormTransaction() {
    // deprecated
  }

  public FormTransaction(Long userId, BigDecimal value, String adress) {
    this.userId = userId;
    this.value = value;
    this.adress = adress;
    this.date = LocalDateTime.now();
  }

  public Transaction convert(UserRepository userRepository, Long id) throws IdNotFoundException {
    Optional<User> user = userRepository.findById(id);
    if (!user.isPresent())
      throw new IdNotFoundException("User id not found!");
    // BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    // String encode = bcrypt.encode(this.adress);
    return new Transaction(this.value, this.adress, user.get());

  }

  public LocalDateTime getDate() {
    return this.date;
  }

  public BigDecimal getValue() {
    return this.value;
  }

  public Long getUserId() {
    return this.userId;
  }

  public String getAdress() {
    return this.adress;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

}