package br.com.midhatdrops.integration.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.midhatdrops.dto.FormTransaction;
import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTests {

  @Autowired
  private MockMvc mock;

  @Autowired
  private TransactionsRepository transactionsRepository;

  @Autowired
  private UserRepository userRepository;

  @Test
  void shouldReturnMVCWhenRequestGET() throws Exception {
    URI uri = new URI("/transacations");
    MvcResult result = mock.perform(MockMvcRequestBuilders.get(uri)).andReturn();
    assertNotNull(result);
  }

  @Test
  void shouldDeleteATransaction() throws Exception {
    URI uri = new URI("/transactions/2");
    mock.perform(MockMvcRequestBuilders.delete(uri)).andExpect(MockMvcResultMatchers.redirectedUrl("/transactions"));
  }

  // @Test
  // void shouldAlterATransactionWithValidInfos() throws Exception {
  // URI uri = new URI("/transactions/3");
  // Transaction transact = transactionsRepository.getOne(Long.valueOf(3));
  // FormTransaction transaction = new FormTransaction(transact.getUser().getId(),
  // BigDecimal.valueOf(300),
  // "Rua itapetininga 2002");
  // mock.perform(MockMvcRequestBuilders.put(uri).content();
  // }
}
