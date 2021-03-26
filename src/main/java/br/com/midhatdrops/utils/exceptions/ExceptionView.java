package br.com.midhatdrops.utils.exceptions;

import org.springframework.web.servlet.ModelAndView;

public interface ExceptionView {
  public abstract ModelAndView exception();
}
