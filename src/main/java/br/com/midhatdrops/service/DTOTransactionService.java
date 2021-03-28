package br.com.midhatdrops.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.midhatdrops.dto.ChangeTransactionForm;
import br.com.midhatdrops.dto.DeleteTransactionForm;
import br.com.midhatdrops.dto.FormTransaction;
import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.repository.UserRepository;
import br.com.midhatdrops.utils.exceptions.IdNotFoundException;
import br.com.midhatdrops.utils.validations.SaldoValidation;

@Service
public class DTOTransactionService {

  public void delete(DeleteTransactionForm deleteTransactionForm, TransactionsRepository transactionsRepository)
      throws IdNotFoundException {
    Transaction transaction = deleteTransactionForm.convert(transactionsRepository);
    transactionsRepository.deleteById(transaction.getId());
  }

  public String save(FormTransaction transaction, UserRepository userRepository,
      TransactionsRepository transactionsRepository) throws IdNotFoundException {
    User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userRepository.getOne(userAuth.getId());
    boolean validation = new SaldoValidation(user.getSaldo(), transaction.getValue()).validate();
    if (validation) {
      Transaction newTransaction = transaction.convert(userRepository, user.getId());
      transactionsRepository.save(newTransaction);
      user.setSaldo(user.getSaldo().subtract(transaction.getValue()));
      userRepository.save(user);
      return "redirect:/transactions";
    }

    return "redirect:/exceptions/saldo";

  }

  public void change(TransactionsRepository transactionsRepository, ChangeTransactionForm transaction)
      throws IdNotFoundException {
    Transaction newTransaction = transaction.convert(transactionsRepository);
    transactionsRepository.save(newTransaction);
  }

  public Page<Transaction> listAll(TransactionsRepository transactionsRepository, Integer page) {
    PageRequest pageRequest = PageRequest.of(page, 5);
    return transactionsRepository.findAll(pageRequest);

  }

  public Page<Transaction> listByUser(TransactionsRepository transactionsRepository, Integer page) {
    PageRequest pageRequest = PageRequest.of(page, 5);
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<Transaction> list = transactionsRepository.findAllUserTransactions(user.getId(), pageRequest);
    return new PageImpl<>(list);

  }

}
