package br.com.midhatdrops.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.midhatdrops.dto.NewUserForm;
import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.UserRepository;
import br.com.midhatdrops.service.DTOUserService;

@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private DTOUserService dtoUserService;

  @GetMapping()
  @RequestMapping("user")
  public String login() {
    return "user/loginPage";
  }

  @GetMapping("/user/newUser")
  public String registerPage(NewUserForm newUserForm) {
    return "user/register";
  }

  @PostMapping("/user/newUser")
  public String register(@Valid NewUserForm newUserForm, BindingResult result) {
    if (result.hasErrors()) {
      return "user/register";
    }
    dtoUserService.save(newUserForm, userRepository);
    // request.login(newUser.getUsername(), newUser.getPassword());
    return "redirect:/login";
  }

}
