package com.paula.TheNextBookSpring.service.db;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.paula.TheNextBookSpring.model.Categoria;
import com.paula.TheNextBookSpring.repository.CategoriaRepository;
import com.paula.TheNextBookSpring.service.ICategoriaService;

@Service
@Primary
public class CategoriaServiceJpa implements ICategoriaService{

	@Autowired
	public CategoriaRepository repo;
	
	public void guardar(Categoria categoria) {
		
		repo.save(categoria);
	}
	
	public Categoria obtenerCategoriaPorId(Integer id) {
		Optional<Categoria> optional = repo.findById(id);
		
		return optional.get();
	}
	
	public List<Categoria> obtenerCategoria(){
		List<Categoria> categorias = new LinkedList<Categoria>();
		
		categorias = repo.findAll();
		
		return categorias;
	}
	
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}
	
	public long conteo() {
		long count = repo.count();
		
		return count;
	}

	public Categoria obtenerCategoriaPorTipo(String tipo) {
		Categoria categoriaRecoger = repo.findByTipo(tipo);
		
		return categoriaRecoger;
	}
}