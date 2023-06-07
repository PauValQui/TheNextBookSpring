package com.paula.TheNextBookSpring.service.db;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.paula.TheNextBookSpring.model.Valoracion;
import com.paula.TheNextBookSpring.repository.ValoracionRepository;
import com.paula.TheNextBookSpring.service.IValoracionService;

@Service
@Primary
public class ValoracionServiceJpa implements IValoracionService {

	@Autowired
	public ValoracionRepository repo;
	
	@Override
	public void guardar(Valoracion Valoracion) {
		repo.save(Valoracion);
	}

	@Override
	public Valoracion obtenerValoracionPorId(Integer id) {
		Optional<Valoracion> optional = repo.findById(id);
		
		return optional.get();
	}

	@Override
	public List<Valoracion> obtenerValoraciones() {
		
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public long conteo() {
		long count = repo.count();
		
		return count;
	}

	@Override
	public List<Valoracion> obtenerValoracionPorLibro(Integer idLibro) {
		List<Valoracion> valoraciones = obtenerValoraciones();
		List<Valoracion> valoracionesPorLibro = new LinkedList<Valoracion>();
		
		for (Valoracion valoracion : valoraciones) {
			if(valoracion.getLibro().getId() == idLibro) {
				valoracionesPorLibro.add(valoracion);
			}
		}
		
		return valoracionesPorLibro;
	}

	@Override
	public List<Valoracion> obtenerValoracionPorUsuario(Integer idUsuario, Integer idLibro) {
		List<Valoracion> valoraciones = obtenerValoraciones();
		List<Valoracion> valoracionesPorUsuario = new LinkedList<Valoracion>();
		
		for (Valoracion valoracion : valoraciones) {
			if(valoracion.getUsuario().getId() == idUsuario && valoracion.getLibro().getId() == idLibro) {
				valoracionesPorUsuario.add(valoracion);
			}
		}
		
		return valoracionesPorUsuario;
	}



}
