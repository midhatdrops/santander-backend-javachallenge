package br.com.midhatdrops.utils.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.utils.commands.GenerateModelAndView;

public class SaldoException implements ExceptionView {

  private String message;

  public SaldoException(String error) {
    this.message = error;
  }

  @Override
  public ModelAndView exception() {
    List<String> error = Arrays.asList(message);
    return new GenerateModelAndView().showException(error);
  }

}
