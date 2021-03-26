package br.com.midhatdrops.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.UserRepository;

public class FormTransaction {

  private LocalDateTime date;
  private BigDecimal value;
  private Long userId;
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

  public Transaction convert(UserRepository userRepository, Long id) {
    User user = userRepository.getOne(id);
    // BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    // String encode = bcrypt.encode(this.adress);
    Transaction transaction = new Transaction(this.value, this.adress, user);
    return transaction;

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