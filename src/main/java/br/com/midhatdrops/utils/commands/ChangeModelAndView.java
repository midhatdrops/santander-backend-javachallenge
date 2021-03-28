package br.com.midhatdrops.utils.commands;

import java.util.Optional;

import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.dto.ChangeTransactionForm;
import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.repository.UserRepository;

public class ChangeModelAndView {
  public ModelAndView execute(ChangeTransactionForm transaction, Long id, TransactionsRepository transactionsRepository,
      UserRepository userRepository) {
    Optional<Transaction> optional = transactionsRepository.findById(id);
    if (!optional.isPresent()) {
      return new GenerateModelAndView().home(transactionsRepository, 0, userRepository);
    }
    ModelAndView mvc = new ModelAndView("transactions/changeForm");
    mvc.addObject("transaction", optional.get());
    return mvc;

  }
}
