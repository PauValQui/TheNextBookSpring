package com.paula.TheNextBookSpring.service.db;


import java.util.LinkedList;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.paula.TheNextBookSpring.model.Libro;
import com.paula.TheNextBookSpring.repository.LibroRepository;
import com.paula.TheNextBookSpring.service.ILibroService;

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
		
		return repo.findAll();
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

	@Override
	public Libro findByTitulo(String Titulo) {
		List<Libro> libros = obtenerLibros();
		Libro libroEncontrado = new Libro();
		boolean indicador = false;
		
		for (Libro libro : libros) {
			if(libro.getTitulo().equals(Titulo)) {
				indicador= true;
				libroEncontrado = libro;
			}
		}
		
		if(indicador == true) {
			return libroEncontrado;
		}else {
			return libroEncontrado;
		}
		
		
	}


	
	
	
}

