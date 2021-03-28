package br.com.midhatdrops.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.utils.exceptions.SaldoException;

@Controller
@RequestMapping("/exceptions")
public class ExceptionsController {

  @GetMapping("/saldo")
  public ModelAndView saldo() {
    return new SaldoException("Saldo insuficiente!").exception();
  }

}
