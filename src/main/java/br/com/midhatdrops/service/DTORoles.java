package br.com.midhatdrops.service;

import org.springframework.stereotype.Service;

import br.com.midhatdrops.models.Role;
import br.com.midhatdrops.repository.RolesRepository;

@Service
public class DTORoles {

  public void save(RolesRepository rolesRepository) {
    Role role = new Role("ROLE_USER");
    rolesRepository.save(role);
  }

}
