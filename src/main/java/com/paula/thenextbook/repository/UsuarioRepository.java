package com.paula.thenextbook.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.paula.thenextbook.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByUsername(String username);
	Optional<Usuario> findByEmail(String email);
}
