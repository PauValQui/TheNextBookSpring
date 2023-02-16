package com.paula.TheNextBookSpring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.paula.TheNextBookSpring.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByUsername(String username);
	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByEstatus(boolean estatus);
	
	@Modifying
    @Query("UPDATE Usuario u SET u.estatus=false WHERE u.id = :paramIdUsuario")
    void lock(@Param("paramIdUsuario") int idUsuario);
	
	@Modifying
    @Query("UPDATE Usuario u SET u.estatus=true WHERE u.id = :paramIdUsuario")
    void unlock(@Param("paramIdUsuario") int idUsuario);
}

