package com.paula.thenextbook.service.db;

import java.util.LinkedList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.paula.thenextbook.model.Autor;
import com.paula.thenextbook.model.Categoria;
import com.paula.thenextbook.model.Libro;
import com.paula.thenextbook.repository.LibroRepository;
import com.paula.thenextbook.service.ILibroService;

@Service
@Primary
public class LibroServiceJpa implements ILibroService{

	@Autowired
	public LibroRepository repo;
	
	public void guardar(Libro libro) {
		
		repo.save(libro);
	}
	
	public Libro obtenerLibroPorId(Integer id) {
		Optional<Libro> optional = repo.findById(id);
		
		return optional.get();
	}
	
	public List<Libro> obtenerLibros(){
		List<Libro> libros = new LinkedList<Libro>();
		
		libros = repo.findAll();
		
		return libros;
	}
	
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}
	
	public long conteo() {
		long count = repo.count();
		
		return count;
	}

	public List<Libro> obtenerLibroPorAutor(Integer idAutor) {
		List<Libro> libros = obtenerLibros();
		List<Libro> librosPorAutor = new LinkedList<Libro>();
		
		for (Libro libro : libros) {
			if(libro.getAutor().getId() == idAutor) {
				librosPorAutor.add(libro);
			}
		}
		
		return librosPorAutor;
	}
	
	public List<Libro> obtenerLibroPorCategoria(Integer idCategoria) {
		List<Libro> libros = obtenerLibros();
		List<Libro> librosPorCategoria = new LinkedList<Libro>();
		
		for (Libro libro : libros) {
			if(libro.getAutor().getId() == idCategoria) {
				librosPorCategoria.add(libro);
			}
		}
		
		return librosPorCategoria;
	}
	
	
}
