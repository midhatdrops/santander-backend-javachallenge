package br.com.midhatdrops.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

  @GetMapping()
  public String home() {
    return "redirect:/transactions/0";
  }

  @GetMapping("/{page}")
  public ModelAndView homeWithPage(@PathVariable(value = "page", required = false) Integer page) {
    return new GenerateModelAndView().home(transactionsRepository, page);
  }

  @GetMapping("cadastro")
  public ModelAndView cadastro(FormTransaction transaction, BindingResult result) {
    return new GenerateModelAndView().newTransaction(transaction);
  }

  @PostMapping("/cadastro")
  public ModelAndView newTransaction(@Valid FormTransaction transaction) throws IdNotFoundException {
    return new DTOService().save(transaction, userRepository, transactionsRepository);
  }

  @GetMapping("/change/{id}")
  public ModelAndView changeTransactionForm(@Valid ChangeTransactionForm transaction,
      @PathVariable(value = "id") Long id) {
    return new GenerateModelAndView().changeTransaction(transaction, id, transactionsRepository);
  }

  @PostMapping("/change")
  public String changeTransaction(ChangeTransactionForm transaction, UserRepository userRepository,
      BindingResult result) throws IdNotFoundException {
    if (result.hasErrors()) {
      return "/transactions/change/" + transaction.getId();
    }
    dtoService.change(transactionsRepository, transaction);
    return "redirect:/transactions";
  }

  @GetMapping("/delete/{id}")
  public ModelAndView deleteTransactionForm(@PathVariable(value = "id") Long id,
      DeleteTransactionForm deleteTransactionForm) {

    return new GenerateModelAndView().deleteTransaction(deleteTransactionForm, id, transactionsRepository);
  }

  @PostMapping("/delete")
  @CacheEvict(value = "pageList")
  public String deleteTransaction(DeleteTransactionForm deleteTransactionForm) throws IdNotFoundException {
    dtoService.delete(deleteTransactionForm, transactionsRepository);
    return "redirect:/transactions";
  }
}
