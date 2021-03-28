package br.com.midhatdrops.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.constraints.Max;
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
  @NotBlank
  @NotEmpty
  private String adress;
  @NotBlank
  @NotEmpty
  private String description;

  @Deprecated
  public FormTransaction() {
    // deprecated
  }

  public FormTransaction(BigDecimal value, String adress, String description) {
    this.value = value;
    this.adress = adress;
    this.date = LocalDateTime.now();
    this.description = description;
  }

  public Transaction convert(UserRepository userRepository, Long id) throws IdNotFoundException {
    Optional<User> optional = userRepository.findById(id);
    if (!optional.isPresent())
      throw new IdNotFoundException("User id not found!");
    return new Transaction(this.value, this.adress, optional.get(), this.description);

  }

  public LocalDateTime getDate() {
    return this.date;
  }

  public BigDecimal getValue() {
    return this.value;
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

  public void setAdress(String adress) {
    this.adress = adress;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}