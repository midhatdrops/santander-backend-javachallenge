package br.com.midhatdrops.utils.commands;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.dto.FormTransaction;

public class NewModelAndView {
  public ModelAndView execute(@Valid FormTransaction transaction) {
    return new ModelAndView("transactions/cadastroForm");
  }
}
