package br.com.midhatdrops.utils.validations;

import java.math.BigDecimal;

public class SaldoValidation implements Validation {
  private BigDecimal saldoAtual;
  private BigDecimal valorTransacao;

  public SaldoValidation(BigDecimal saldoAtual, BigDecimal valorTransacao) {
    this.saldoAtual = saldoAtual;
    this.valorTransacao = valorTransacao;
  }

  @Override
  public boolean validate() {

    if (saldoAtual.compareTo(valorTransacao) >= 0) {
      return true;
    } else {
      return false;
    }

  }

}
