package br.com.midhatdrops.integration.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class UserIntegrationTests {

  @Autowired
  private UserRepository userRepository;

  @Test
  void itShouldFindAnUser() {
    User user = userRepository.getOne(Long.valueOf(1));
    assertNotNull(user);
  }

  @Test
  void itShouldAddAnUser() {
    User user = new User("Maria", "Rua Itopinga 2939", "0909090", "0909909", BigDecimal.valueOf(8000));
    userRepository.save(user);
    User newUser = userRepository.findByName(user.getName());
    assertEquals("Maria", newUser.getName());

  }
}
