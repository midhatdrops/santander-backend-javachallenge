package br.com.midhatdrops.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

  @org.springframework.cache.annotation.Cacheable(value = "homeList")
  @GetMapping()
  public ModelAndView home() {
    return new GenerateModelAndView().home(transactionsRepository, 0);
  }

  @GetMapping("/{page}")
  @org.springframework.cache.annotation.Cacheable(value = "pageList")
  public ModelAndView homeWithPage(@PathVariable(value = "page", required = false) Integer page) {
    return new GenerateModelAndView().home(transactionsRepository, page);
  }

  @GetMapping("cadastro")
  public ModelAndView cadastro(@Valid FormTransaction transaction, BindingResult result) {
    return new GenerateModelAndView().newTransaction(transaction, result);
  }

  @PostMapping
  @CacheEvict(value = "pageList")
  public ModelAndView newTransaction(FormTransaction transaction, BindingResult result) throws IdNotFoundException {
    return new DTOService().save(transaction, userRepository, transactionsRepository, result);
  }

  @GetMapping("/change/{id}")
  public ModelAndView changeTransactionForm(ChangeTransactionForm transaction, @PathVariable(value = "id") Long id) {
    return new GenerateModelAndView().changeTransaction(transaction, id, transactionsRepository);
  }

  @PostMapping("/change")
  @CacheEvict(value = "pageList")
  public String changeTransaction(ChangeTransactionForm transaction, UserRepository userRepository)
      throws IdNotFoundException {
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
  public ModelAndView deleteTransaction(DeleteTransactionForm deleteTransactionForm) throws IdNotFoundException {
    dtoService.delete(deleteTransactionForm, transactionsRepository);
    return new GenerateModelAndView().home(transactionsRepository, 0);
  }
}
