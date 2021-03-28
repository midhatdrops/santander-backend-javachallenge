package br.com.midhatdrops.utils.commands;

import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.models.User;

public class DashboardModelAndView {
  public ModelAndView execute(User user) {
    ModelAndView mvc = new ModelAndView("user/dashboard");
    mvc.addObject("user", user);
    return mvc;
  }
}
