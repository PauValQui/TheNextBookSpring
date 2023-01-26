package com.paula.thenextbook.service.db;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.paula.thenextbook.model.Valoracion;
import com.paula.thenextbook.repository.ValoracionRepository;
import com.paula.thenextbook.service.IValoracionService;

@Service
@Primary
public class ValoracionServiceJpa implements IValoracionService{

	@Autowired
	public ValoracionRepository repo;
	
	public void guardar(Valoracion valoracion) {
		
		repo.save(valoracion);
	}
	
	public Valoracion obtenerValoracionPorId(Integer id) {
		Optional<Valoracion> valoracion = repo.findById(id);
		
		return valoracion.get();
	}
	
	public List<Valoracion> obtenerValoracionPorLibro(Integer idLibro) {
		List<Valoracion> valoraciones = obtenerValoracion();
		List<Valoracion> valoracionRecoger = new LinkedList<Valoracion>();
		
		for (Valoracion valoracion : valoraciones) {
			if(valoracion.getLibro().getId() == idLibro) {
				valoracionRecoger.add(valoracion);
			}
		}
		
		return valoracionRecoger;//Muestra todas las valoraciones del libro
	}
	
	public List<Valoracion>	obtenerValoracionPorUsuario(Integer idUsuario, Integer idLibro) {
		List<Valoracion> valoraciones = obtenerValoracion();
		List<Valoracion> valoracionRecoger = new LinkedList<Valoracion>();
		
		for (Valoracion valoracion : valoraciones) {
			if(valoracion.getLibro().getId() == idLibro && valoracion.getUsuario().getId() == idUsuario) {
				valoracionRecoger.add(valoracion);
			}
		}
		
		return valoracionRecoger; //Si se registra el usuario cuando entre en el libro verá las valoraciones echas por ese usuario
	}
	
	public List<Valoracion> obtenerValoracion(){
		List<Valoracion> valoraciones = new LinkedList<Valoracion>();
		
		valoraciones = repo.findAll();
		
		return valoraciones;
	}

	
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}
	
	public long conteo() {
		long count = repo.count();
		
		return count;
	}

}
