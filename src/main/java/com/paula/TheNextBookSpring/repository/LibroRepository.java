package com.paula.TheNextBookSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paula.TheNextBookSpring.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

}
