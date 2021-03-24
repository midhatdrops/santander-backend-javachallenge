package br.com.midhatdrops.unity.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.midhatdrops.models.User;

public class UserTest {
  @Test
  void shouldCreateAnUserWithValidInfos() {
    User user = new User("Leonardo", "Rua Itapeti 1019", "8798787", "989898989", BigDecimal.valueOf(200));
    String username = "Leonardo";
    assertEquals(username, user.getName());
  }
}
