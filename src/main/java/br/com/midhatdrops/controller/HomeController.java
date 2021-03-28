package br.com.midhatdrops.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class HomeController {
  @GetMapping
  public String redirectDashboard() {
    return "redirect:/dashboard";
  }
}