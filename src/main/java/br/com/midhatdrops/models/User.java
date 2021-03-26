package br.com.midhatdrops.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String adress;
  private String cardNumber;
  private String cardPassword;
  private BigDecimal saldo;

  public BigDecimal getSaldo() {
    return this.saldo;
  }

  public void setSaldo(BigDecimal saldo) {
    this.saldo = saldo;
  }

  @Deprecated
  public User() {
    // deprecated
  }

  public User(String name, String adress, String cardNumber, String cardPassword, BigDecimal saldo) {
    this.name = name;
    this.adress = adress;
    this.cardNumber = cardNumber;
    this.cardPassword = cardPassword;
    this.saldo = saldo;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAdress() {
    return this.adress;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

  public String getCardNumber() {
    return this.cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardPassword() {
    return this.cardPassword;
  }

  public void setCardPassword(String cardPassword) {
    this.cardPassword = cardPassword;
  }

}
