package br.com.midhatdrops.dto;

import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.utils.exceptions.IdNotFoundException;

public interface Form {
  public Transaction convert(TransactionsRepository repository) throws IdNotFoundException;
}
