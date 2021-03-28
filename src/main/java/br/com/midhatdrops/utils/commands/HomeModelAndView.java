package br.com.midhatdrops.utils.commands;

import org.springframework.data.domain.Page;
import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.service.DTOTransactionService;
import br.com.midhatdrops.utils.validations.PageValidation;

public class HomeModelAndView {

  public ModelAndView execute(TransactionsRepository transactionsRepository, Integer page) {
    boolean validation = new PageValidation(page).validate();
    if (validation) {
      Page<Transaction> list = new DTOTransactionService().listByUser(transactionsRepository, page);
      ModelAndView mvc = new ModelAndView("transactions/home");
      mvc.addObject("transactions", list);
      mvc.addObject("prevPage", page - 1);
      mvc.addObject("nextPage", page + 1);
      return mvc;
    }
    Page<Transaction> list = new DTOTransactionService().listByUser(transactionsRepository, 0);
    ModelAndView mvc = new ModelAndView("transactions/home");
    mvc.addObject("transactions", list);
    mvc.addObject("page", 0);
    mvc.addObject("prevPage", -1);
    mvc.addObject("nextPage", 1);
    return mvc;
  }

}
