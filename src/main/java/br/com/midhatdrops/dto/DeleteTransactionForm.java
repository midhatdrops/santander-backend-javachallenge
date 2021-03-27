package br.com.midhatdrops.dto;

import java.util.Optional;

import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.repository.UserRepository;
import br.com.midhatdrops.utils.exceptions.IdNotFoundException;

public class DeleteTransactionForm implements Form {

  private Long id;

  @Deprecated
  public DeleteTransactionForm() {
    // deprecated
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Transaction convert(TransactionsRepository repository) throws IdNotFoundException {
    Optional<Transaction> optional = repository.findById(this.id);
    if (!optional.isPresent()) {
      throw new IdNotFoundException("Id Not found");
    }
    return optional.get();

  }

  @Override
  public User convert(UserRepository repository) {
    return null;
  }

}
