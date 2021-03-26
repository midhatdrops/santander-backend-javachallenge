package br.com.midhatdrops.utils.commands;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

public class ExceptionModelAndView {
  ModelAndView execute(List<String> error) {
    ModelAndView mvc = new ModelAndView("exceptions/templateException");
    mvc.addObject("errors", error);
    return mvc;
  }
}
