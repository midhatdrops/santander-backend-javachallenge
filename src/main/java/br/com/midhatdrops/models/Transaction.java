package br.com.midhatdrops.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private BigDecimal value;
  private LocalDateTime date;
  private String adress;
  @ManyToOne(fetch = FetchType.EAGER)
  private User user;

  private String description;

  @Deprecated
  public Transaction() {
    // deprecated
  }

  public Transaction(BigDecimal value, String adress, User user, String description) {
    this.value = value;
    this.adress = adress;
    this.user = user;
    this.date = LocalDateTime.now();
    this.description = description;
  }

  public Transaction(Long id, BigDecimal value, LocalDateTime date, String adress, User user) {
    this.id = id;
    this.value = value;
    this.date = date;
    this.adress = adress;
    this.user = user;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getValue() {
    return this.value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public LocalDateTime getDate() {
    return this.date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public String getAdress() {
    return this.adress;
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
