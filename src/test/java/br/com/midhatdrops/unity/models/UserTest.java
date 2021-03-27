package br.com.midhatdrops.unity.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.midhatdrops.models.User;

public class UserTest {
  @Test
  void shouldCreateAnUserWithValidInfos() {
    User user = new User("Osvaldin", "asauhsasha", "paulao", "paulao123", new BigDecimal("500"));
    String username = "Leonardo";
    assertEquals(username, user.getName());
  }
}
