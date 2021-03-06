package br.com.midhatdrops.utils.commands;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.dto.ChangeTransactionForm;
import br.com.midhatdrops.dto.DeleteTransactionForm;
import br.com.midhatdrops.dto.FormTransaction;
import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.TransactionsRepository;

public class GenerateModelAndView {
  public ModelAndView home(TransactionsRepository transactionsRepository, Integer page) {

    return new HomeModelAndView().execute(transactionsRepository, page);
  }

  public ModelAndView newTransaction(@Valid FormTransaction transaction) {
    return new NewModelAndView().execute(transaction);
  }

  public ModelAndView changeTransaction(ChangeTransactionForm transaction, Long id,
      TransactionsRepository transactionsRepository) {
    return new ChangeModelAndView().execute(transaction, id, transactionsRepository);
  }

  public ModelAndView deleteTransaction(DeleteTransactionForm deleteTransactionForm, Long id,
      TransactionsRepository transactionsRepository) {
    return new DeleteModelAndView().execute(id, deleteTransactionForm, transactionsRepository);
  }

  public ModelAndView showException(List<String> error) {
    return new ExceptionModelAndView().execute(error);
  }

  public ModelAndView dashboard(User user) {
    return new DashboardModelAndView().execute(user);
  }
}
