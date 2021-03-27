package br.com.midhatdrops.service;

import java.util.Arrays;

import org.hibernate.mapping.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
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
public class DTOTransactionService {
  public void delete(DeleteTransactionForm deleteTransactionForm, TransactionsRepository transactionsRepository)
      throws IdNotFoundException {
    Transaction transaction = deleteTransactionForm.convert(transactionsRepository);
    transactionsRepository.deleteById(transaction.getId());
  }

  public ModelAndView save(FormTransaction transaction, UserRepository userRepository,
      TransactionsRepository transactionsRepository) throws IdNotFoundException {
    User user = userRepository.getOne(transaction.getUserId());
    boolean validation = new SaldoValidation(user.getSaldo(), transaction.getValue()).validate();
    if (validation) {
      Transaction newTransaction = transaction.convert(userRepository, transaction.getUserId());
      transactionsRepository.save(newTransaction);
      return new GenerateModelAndView().home(transactionsRepository, 0);
    }

    return new SaldoException("Saldo insuficiente!").exception();

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
