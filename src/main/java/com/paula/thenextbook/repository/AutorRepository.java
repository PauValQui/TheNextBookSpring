package com.paula.thenextbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import com.paula.thenextbook.model.Autor;

public interface AutorRepository extends ListCrudRepository<Autor, Integer>, JpaRepository<Autor, Integer>{

	public Autor findByNombre(String nombre);
}
