package com.paula.thenextbook.service;

import java.util.List;

import com.paula.thenextbook.model.Valoracion;

public interface IValoracionService {
	
	public void guardar(Valoracion valoracion);
	public Valoracion obtenerValoracionPorId(Integer id);
	public List<Valoracion> obtenerValoracion();
	public void eliminar(Integer id);
	public long conteo();
//	public List<Valoracion> obtenerValoracionPorLibro(Integer idLibro);
//	public List<Valoracion>	obtenerValoracionPorUsuario(Integer idUsuario, Integer idLibro);
}
