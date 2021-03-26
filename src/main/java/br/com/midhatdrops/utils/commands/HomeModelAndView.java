package br.com.midhatdrops.utils.commands;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.repository.TransactionsRepository;

public class HomeModelAndView {

  public ModelAndView execute(TransactionsRepository transactionsRepository) {
    List<Transaction> list = transactionsRepository.findAll();
    ModelAndView mvc = new ModelAndView("transactions/home");
    mvc.addObject("transactions", list);
    return mvc;
  }

}
