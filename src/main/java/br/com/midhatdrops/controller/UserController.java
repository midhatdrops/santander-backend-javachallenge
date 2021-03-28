package br.com.midhatdrops.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.midhatdrops.dto.NewUserForm;
import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.UserRepository;
import br.com.midhatdrops.service.DTOUserService;
import br.com.midhatdrops.utils.commands.GenerateModelAndView;

@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private DTOUserService dtoUserService;

  @Autowired
  private AuthenticationManager authManager;

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
    User newUser = dtoUserService.save(newUserForm, userRepository);
    UsernamePasswordAuthenticationToken loginData = new UsernamePasswordAuthenticationToken(newUser.getUsername(),
        newUser.getPassword());
    authManager.authenticate(loginData);
    return "redirect:/transactions";
  }

  @GetMapping("/dashboard")
  public ModelAndView dashboard(Authentication authenticate) {
    User validatedUser = dtoUserService.findWithValidation(authenticate);
    return new GenerateModelAndView().dashboard(validatedUser);
  }

}
