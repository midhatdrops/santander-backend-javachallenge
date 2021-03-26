package br.com.midhatdrops.unity.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.midhatdrops.models.Transaction;

public class TransactionTest {

  @Test
  void shouldCreateATransactionWithValidInfos() {
    Transaction transaction = new Transaction();
    transaction.setValue(BigDecimal.valueOf(400));
    BigDecimal value = new BigDecimal(400);
    assertEquals(value, transaction.getValue());

  }

}
