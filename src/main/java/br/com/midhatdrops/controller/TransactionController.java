package br.com.midhatdrops.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.dto.ChangeTransactionForm;
import br.com.midhatdrops.dto.DeleteTransactionForm;
import br.com.midhatdrops.dto.FormTransaction;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.repository.UserRepository;
import br.com.midhatdrops.service.DTOService;
import br.com.midhatdrops.utils.commands.GenerateModelAndView;
import br.com.midhatdrops.utils.exceptions.IdNotFoundException;

@Controller
@RequestMapping("transactions")
public class TransactionController {

  @Autowired
  private TransactionsRepository transactionsRepository;
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private DTOService dtoService;

  @GetMapping
  public ModelAndView home() {
    return new GenerateModelAndView().home(transactionsRepository);
  }

  @GetMapping("cadastro")
  public ModelAndView cadastro(FormTransaction transaction) {
    return new GenerateModelAndView().newTransaction(transaction);
  }

  @PostMapping
  public ModelAndView newTransaction(FormTransaction transaction) throws IdNotFoundException {
    dtoService.save(transaction, userRepository, transactionsRepository);
    return new GenerateModelAndView().home(transactionsRepository);
  }

  @GetMapping("/change/{id}")
  public ModelAndView changeTransactionForm(ChangeTransactionForm transaction, @PathVariable(value = "id") Long id) {
    return new GenerateModelAndView().changeTransaction(transaction, id, transactionsRepository);
  }

  @PostMapping("/change")
  public ModelAndView changeTransaction(ChangeTransactionForm transaction, UserRepository userRepository)
      throws IdNotFoundException {
    dtoService.change(transactionsRepository, transaction);
    return new GenerateModelAndView().home(transactionsRepository);
  }

  @GetMapping("/delete/{id}")
  public ModelAndView deleteTransactionForm(@PathVariable(value = "id") Long id,
      DeleteTransactionForm deleteTransactionForm) {

    return new GenerateModelAndView().deleteTransaction(deleteTransactionForm, id, transactionsRepository);
  }

  @PostMapping("/delete")
  public ModelAndView deleteTransaction(DeleteTransactionForm deleteTransactionForm) throws IdNotFoundException {
    dtoService.delete(deleteTransactionForm, transactionsRepository);
    return new GenerateModelAndView().home(transactionsRepository);
  }
}
