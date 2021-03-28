package br.com.midhatdrops.service;

import org.springframework.security.core.Authentication;
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

  public User findWithValidation(Authentication authenticate) {
    return (User) authenticate.getPrincipal();
  }

}
