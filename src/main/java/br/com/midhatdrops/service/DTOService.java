package br.com.midhatdrops.service;

import org.springframework.stereotype.Service;

import br.com.midhatdrops.dto.ChangeTransactionForm;
import br.com.midhatdrops.dto.DeleteTransactionForm;
import br.com.midhatdrops.dto.FormTransaction;
import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.repository.UserRepository;
import br.com.midhatdrops.utils.exceptions.IdNotFoundException;

@Service
public class DTOService {
  public void delete(DeleteTransactionForm deleteTransactionForm, TransactionsRepository transactionsRepository)
      throws IdNotFoundException {
    Transaction transaction = deleteTransactionForm.convert(transactionsRepository);
    transactionsRepository.deleteById(transaction.getId());
  }

  public void save(FormTransaction transaction, UserRepository userRepository,
      TransactionsRepository transactionsRepository) throws IdNotFoundException {
    Transaction newTransaction = transaction.convert(userRepository, transaction.getUserId());
    transactionsRepository.save(newTransaction);
  }

  public void change(TransactionsRepository transactionsRepository, ChangeTransactionForm transaction)
      throws IdNotFoundException {
    Transaction newTransaction = transaction.convert(transactionsRepository);
    transactionsRepository.save(newTransaction);
  }
}
