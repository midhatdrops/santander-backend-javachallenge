package br.com.midhatdrops.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.midhatdrops.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
  public User findByName(String name);

  public Optional<User> findByUsername(String username);
}
