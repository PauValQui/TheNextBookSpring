package com.paula.thenextbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paula.thenextbook.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
