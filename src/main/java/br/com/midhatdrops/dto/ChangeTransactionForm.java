package br.com.midhatdrops.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.TransactionsRepository;
import javassist.NotFoundException;

public class ChangeTransactionForm {
  private Long id;
  private BigDecimal value;
  private LocalDateTime date;
  private String adress;
  private User user;

  @Deprecated
  public ChangeTransactionForm() {
    // Spring Boot use
  }

  public ChangeTransactionForm(Long id, BigDecimal value, String adress, User user) {
    this.id = id;
    this.value = value;
    this.adress = adress;
    this.user = user;
  }

  public Long getId() {
    return this.id;
  }

  public BigDecimal getValue() {
    return this.value;
  }

  public LocalDateTime getDate() {
    return this.date;
  }

  public String getAdress() {
    return this.adress;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Transaction convert(TransactionsRepository transactionRepository) throws NotFoundException {
    Optional<Transaction> optional = transactionRepository.findById(this.getId());
    if (!optional.isPresent())
      throw new NotFoundException("Id not found!");
    return new Transaction(this.id, this.value, this.date, this.adress, optional.get().getUser());

  }
}
