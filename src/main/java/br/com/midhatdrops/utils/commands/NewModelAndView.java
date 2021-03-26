package br.com.midhatdrops.utils.commands;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.dto.FormTransaction;

public class NewModelAndView {
  public ModelAndView execute(FormTransaction transaction, BindingResult result) {
    return new ModelAndView("transactions/cadastroForm");
  }
}
