package com.paula.thenextbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.ListCrudRepository;

import com.paula.thenextbook.model.Libro;

//public interface LibroRepository extends ListCrudRepository<Libro, Integer> {
//	public List<Libro> findByIdAutor(Integer idAutor);
//  public List<Libro> findByIdCategoria(Integer idCategoria);


public interface LibroRepository extends JpaRepository<Libro, Integer> {

}
