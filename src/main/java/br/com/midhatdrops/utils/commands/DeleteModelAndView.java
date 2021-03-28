package br.com.midhatdrops.utils.commands;

import java.util.Optional;

import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.dto.DeleteTransactionForm;
import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.repository.UserRepository;

public class DeleteModelAndView {
  public ModelAndView execute(Long id, DeleteTransactionForm deleteTransactionForm,
      TransactionsRepository transactionsRepository, UserRepository userRepository) {
    Optional<Transaction> optional = transactionsRepository.findById(id);
    if (!optional.isPresent())
      new GenerateModelAndView().home(transactionsRepository, 0, userRepository);
    User user = optional.get().getUser();
    ModelAndView mvc = new ModelAndView("transactions/deleteForm");
    mvc.addObject("transaction", optional.get());
    mvc.addObject("user", user);
    return mvc;

  }
}
