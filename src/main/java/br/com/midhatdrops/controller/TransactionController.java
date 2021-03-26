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
import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.repository.UserRepository;
import br.com.midhatdrops.utils.commands.GenerateModelAndView;
import javassist.NotFoundException;

@Controller
@RequestMapping("transactions")
public class TransactionController {

  @Autowired
  private TransactionsRepository transactionsRepository;
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public ModelAndView home() {
    return new GenerateModelAndView().home(transactionsRepository);
  }

  @GetMapping("cadastro")
  public ModelAndView cadastro(FormTransaction transaction) {
    return new GenerateModelAndView().newTransaction("transactions/cadastroForm", transaction);
  }

  @PostMapping
  public ModelAndView newTransaction(FormTransaction transaction) {
    Transaction newTransaction = transaction.convert(userRepository, transaction.getUserId());
    transactionsRepository.save(newTransaction);
    return new GenerateModelAndView().home(transactionsRepository);
  }

  @GetMapping("/change/{id}")
  public ModelAndView changeTransactionForm(ChangeTransactionForm transaction, @PathVariable(value = "id") Long id) {
    return new GenerateModelAndView().changeTransaction(transaction, id, transactionsRepository);
  }

  @PostMapping("/change")
  public ModelAndView changeTransaction(ChangeTransactionForm transaction, UserRepository userRepository)
      throws NotFoundException {
    Transaction newTransaction = transaction.convert(transactionsRepository);
    transactionsRepository.save(newTransaction);
    return new GenerateModelAndView().home(transactionsRepository);
  }

  @GetMapping("/delete/{id}")
  public ModelAndView deleteTransactionForm(@PathVariable(value = "id") Long id,
      DeleteTransactionForm deleteTransactionForm) {

    return new GenerateModelAndView().deleteTransaction(deleteTransactionForm, id, transactionsRepository);
  }

  @PostMapping("/delete")
  public ModelAndView deleteTransaction(DeleteTransactionForm deleteTransactionForm) throws NotFoundException {
    Transaction transaction = deleteTransactionForm.convert(transactionsRepository);
    transactionsRepository.deleteById(transaction.getId());
    return new GenerateModelAndView().home(transactionsRepository);
  }
}
