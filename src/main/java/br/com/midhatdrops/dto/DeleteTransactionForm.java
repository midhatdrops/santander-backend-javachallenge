package br.com.midhatdrops.dto;

import java.util.Optional;

import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.repository.TransactionsRepository;
import javassist.NotFoundException;

public class DeleteTransactionForm {

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

  public Transaction convert(TransactionsRepository repository) throws NotFoundException {
    Optional<Transaction> optional = repository.findById(this.id);
    if (!optional.isPresent()) {
      throw new NotFoundException("Not found");
    }
    return optional.get();

  }

}
