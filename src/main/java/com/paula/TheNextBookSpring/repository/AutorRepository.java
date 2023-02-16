package com.paula.TheNextBookSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paula.TheNextBookSpring.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer>{

	public Autor findByNombre(String nombre);
}