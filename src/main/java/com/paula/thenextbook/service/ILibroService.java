package com.paula.thenextbook.service;

import java.util.List;

import com.paula.thenextbook.model.Libro;

public interface ILibroService {
	
	public void guardar(Libro libro);
	public Libro obtenerLibroPorId(Integer id);
	public List<Libro> obtenerLibros();
	public void eliminar(Integer id);
	public long conteo();
	public List<Libro> obtenerLibroPorAutor(Integer idAutor);
	public List<Libro> obtenerLibroPorCategoria(Integer idCategoria);
	
}
