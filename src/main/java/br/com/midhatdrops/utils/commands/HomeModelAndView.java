package br.com.midhatdrops.utils.commands;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.models.Transaction;
import br.com.midhatdrops.repository.TransactionsRepository;
import br.com.midhatdrops.service.DTOService;
import br.com.midhatdrops.utils.validations.PageValidation;

public class HomeModelAndView {

  public ModelAndView execute(TransactionsRepository transactionsRepository, Integer page) {
    boolean validation = new PageValidation(page).validate();
    if (validation) {
      Page<Transaction> list = new DTOService().list(transactionsRepository, page);
      ModelAndView mvc = new ModelAndView("transactions/home");
      mvc.addObject("transactions", list);
      return mvc;
    }
    Page<Transaction> list = new DTOService().list(transactionsRepository, 0);
    ModelAndView mvc = new ModelAndView("transactions/home");
    mvc.addObject("transactions", list);
    return mvc;
  }

}
