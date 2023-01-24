package com.paula.thenextbook.repository;

import org.springframework.data.repository.CrudRepository;
import com.paula.thenextbook.model.Libro;

public interface LibroRepository extends CrudRepository<Libro, Integer> {
	
}
