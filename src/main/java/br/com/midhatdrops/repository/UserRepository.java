package br.com.midhatdrops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.midhatdrops.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
