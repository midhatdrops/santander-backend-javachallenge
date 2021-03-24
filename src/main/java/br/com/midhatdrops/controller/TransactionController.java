package br.com.midhatdrops.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.dto.FormTransaction;
import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.repository.UserRepository;

@Controller
@RequestMapping("transactions")
public class TransactionController {

  @Autowired
  private TransactionsRepository transactionsRepository;
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public ModelAndView home() {
    List<Transaction> list = transactionsRepository.findAll();
    ModelAndView mvc = new ModelAndView("transactions/home");
    mvc.addObject("transactions", list);
    return mvc;
  }

  @GetMapping("cadastro")
  public ModelAndView cadastro() {
    return new ModelAndView("transactions/cadastroForm");
  }

  @PostMapping
  public ModelAndView newTransaction(FormTransaction transaction) {
    Transaction newTransaction = transaction.convert(userRepository, transaction.getUserId());
    transactionsRepository.save(newTransaction);
    List<Transaction> list = transactionsRepository.findAll();
    ModelAndView mvc = new ModelAndView("transactions/home");
    mvc.addObject("transactions", list);
    return mvc;
  }

}
