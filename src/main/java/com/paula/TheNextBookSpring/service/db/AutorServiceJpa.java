package com.paula.TheNextBookSpring.service.db;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.paula.TheNextBookSpring.model.Autor;
import com.paula.TheNextBookSpring.repository.AutorRepository;
import com.paula.TheNextBookSpring.service.IAutorService;

@Service
@Primary
public class AutorServiceJpa implements IAutorService{

	@Autowired
	public AutorRepository repo;
	
	public void guardar(Autor autor) {
		
		repo.save(autor);
	}
	
	public Autor obtenerAutorPorId(Integer id) {
		Optional<Autor> optional = repo.findById(id);
		
		return optional.get();
	}
	
	public Autor obtenerAutorPorNombre(String nombre) {
		Autor autorRecoger = repo.findByNombre(nombre);
		
		return autorRecoger;
	}
	
	public List<Autor> obtenerAutores(){
		List<Autor> autores = new LinkedList<Autor>();
		
		autores = repo.findAll();
		
		return autores;
	}

	
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}
	
	public long conteo() {
		long count = repo.count();
		
		return count;
	}
}
