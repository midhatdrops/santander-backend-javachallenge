package br.com.midhatdrops.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.dto.ChangeTransactionForm;
import br.com.midhatdrops.dto.DeleteTransactionForm;
import br.com.midhatdrops.dto.FormTransaction;
import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.repository.UserRepository;
import br.com.midhatdrops.utils.commands.GenerateModelAndView;
import br.com.midhatdrops.utils.exceptions.IdNotFoundException;
import br.com.midhatdrops.utils.exceptions.SaldoException;
import br.com.midhatdrops.utils.validations.SaldoValidation;

@Service
public class DTOService {
  public void delete(DeleteTransactionForm deleteTransactionForm, TransactionsRepository transactionsRepository)
      throws IdNotFoundException {
    Transaction transaction = deleteTransactionForm.convert(transactionsRepository);
    transactionsRepository.deleteById(transaction.getId());
  }

  public ModelAndView save(FormTransaction transaction, UserRepository userRepository,
      TransactionsRepository transactionsRepository, BindingResult result) throws IdNotFoundException {
    if (result.hasErrors()) {
      ModelAndView mvc = new ModelAndView("transactions/cadastroForm");
      List<ObjectError> allErrors = result.getAllErrors();
      mvc.addObject("errors", allErrors);
      System.out.println(allErrors.get(0).getDefaultMessage());
      return mvc;
    }
    User user = userRepository.getOne(transaction.getUserId());
    boolean validation = new SaldoValidation(user.getSaldo(), transaction.getValue()).validate();
    System.out.println(validation);
    if (validation) {
      Transaction newTransaction = transaction.convert(userRepository, transaction.getUserId());
      transactionsRepository.save(newTransaction);
      return new GenerateModelAndView().home(transactionsRepository, 0);
    }
    return new SaldoException("Saldo Insuficiente!").exception();

  }

  public void change(TransactionsRepository transactionsRepository, ChangeTransactionForm transaction)
      throws IdNotFoundException {
    Transaction newTransaction = transaction.convert(transactionsRepository);
    transactionsRepository.save(newTransaction);
  }

  public Page<Transaction> list(TransactionsRepository transactionsRepository, Integer page) {
    PageRequest pageRequest = PageRequest.of(page, 5);
    return transactionsRepository.findAll(pageRequest);
  }
}
