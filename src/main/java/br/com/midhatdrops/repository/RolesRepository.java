package br.com.midhatdrops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.midhatdrops.models.Role;

public interface RolesRepository extends JpaRepository<Role, Long> {

}
