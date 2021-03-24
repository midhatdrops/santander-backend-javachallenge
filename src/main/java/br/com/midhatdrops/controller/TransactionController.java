package br.com.midhatdrops.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.repository.TransactionsRepository;

@Controller
@RequestMapping("transactions")
public class TransactionController {

  @Autowired
  private TransactionsRepository transactionsRepository;

  @GetMapping
  public ModelAndView home() {
    List<Transaction> list = transactionsRepository.findAll();
    ModelAndView mvc = new ModelAndView("transactions/home");
    mvc.addObject("transactions", list);
    return mvc;
  }

}
