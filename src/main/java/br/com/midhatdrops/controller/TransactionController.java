package br.com.midhatdrops.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.dto.DeleteTransactionForm;
import br.com.midhatdrops.dto.FormTransaction;
import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.repository.UserRepository;
import javassist.NotFoundException;

@Controller
@RequestMapping("transactions")
public class TransactionController {

  @Autowired
  private TransactionsRepository transactionsRepository;
  @Autowired
  private UserRepository userRepository;

  private final String REDIRECT_HOMEPAGE = "redirect:/transactions";

  @GetMapping
  public ModelAndView home() {
    List<Transaction> list = transactionsRepository.findAll();
    ModelAndView mvc = new ModelAndView("transactions/home");
    mvc.addObject("transactions", list);
    return mvc;
  }

  @GetMapping("cadastro")
  public ModelAndView cadastro(FormTransaction transaction) {
    return new ModelAndView("transactions/cadastroForm");
  }

  @PostMapping
  public String newTransaction(FormTransaction transaction) {
    Transaction newTransaction = transaction.convert(userRepository, transaction.getUserId());
    transactionsRepository.save(newTransaction);
    return REDIRECT_HOMEPAGE;
  }

  @PutMapping("/{id}")
  public String changeTransaction(FormTransaction transaction, @PathVariable(value = "id") Long id) {
    Transaction newTransaction = transaction.convert(userRepository, id);
    try {
      transactionsRepository.save(newTransaction);
      return REDIRECT_HOMEPAGE;
    } catch (Exception e) {
      return e.getMessage();
    }

  }

  @GetMapping("/delete/{id}")
  public ModelAndView deleteTransactionForm(@PathVariable(value = "id") Long id) {
    Optional<Transaction> optional = transactionsRepository.findById(id);
    if (!optional.isPresent()) {
      List<Transaction> list = transactionsRepository.findAll();
      ModelAndView mvc = new ModelAndView("transactions/home");
      mvc.addObject("transactions", list);
      return mvc;

    }
    User user = optional.get().getUser();
    ModelAndView mvc = new ModelAndView("transactions/deleteForm");
    mvc.addObject("transaction", optional.get());
    mvc.addObject("user", user);
    return mvc;
  }

  @PostMapping("/delete/{id}")
  public String deleteTransaction(@PathVariable(value = "id") Long id) {
    transactionsRepository.deleteById(id);
    return "redirect:/transactions";
  }
}
