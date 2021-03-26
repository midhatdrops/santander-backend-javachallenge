package br.com.midhatdrops.utils.commands;

import org.springframework.web.servlet.ModelAndView;

public class GenerateModelAndView {
  public ModelAndView execute(String uri, String atribute, Object obj) {
    if (atribute == null || obj == null) {
      return new ModelAndView(uri);
    }
    ModelAndView mvc = new ModelAndView(uri);
    mvc.addObject(atribute, obj);
    return mvc;
  }
}
