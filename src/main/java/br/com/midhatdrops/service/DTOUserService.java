package br.com.midhatdrops.service;

import java.math.BigDecimal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.midhatdrops.dto.NewUserForm;
import br.com.midhatdrops.models.User;
import br.com.midhatdrops.repository.UserRepository;

@Service
public class DTOUserService {

  public User save(NewUserForm userForm, UserRepository userRepository) {
    User newUser = userForm.convert(userRepository);
    return userRepository.save(newUser);
  }

  public User findWithValidation(UserRepository userRepository) {
    User authenticate = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return userRepository.getOne(authenticate.getId());

  }

  public void debit(BigDecimal value, User user) {
    user.setSaldo(user.getSaldo().subtract(value));
  }

}
