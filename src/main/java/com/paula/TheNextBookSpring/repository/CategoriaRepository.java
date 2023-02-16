package com.paula.TheNextBookSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import com.paula.TheNextBookSpring.model.Categoria;

public interface CategoriaRepository extends ListCrudRepository<Categoria, Integer>, JpaRepository<Categoria, Integer> {

	public Categoria findByTipo(String tipo);
	
}

