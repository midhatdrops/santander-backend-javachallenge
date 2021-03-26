package br.com.midhatdrops.utils.commands;

import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.dto.FormTransaction;

public class NewModelAndView {
  public ModelAndView execute(FormTransaction transaction) {
    return new ModelAndView("transactions/cadastroForm");
  }
}
