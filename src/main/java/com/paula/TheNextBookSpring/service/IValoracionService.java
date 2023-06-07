package com.paula.TheNextBookSpring.service;

import java.util.List;

import com.paula.TheNextBookSpring.model.Valoracion;

public interface IValoracionService {
	
	public void guardar(Valoracion Valoracion);
	public Valoracion obtenerValoracionPorId(Integer id);
	public List<Valoracion> obtenerValoraciones();
	public void eliminar(Integer id);
	public long conteo();
	public List<Valoracion> obtenerValoracionPorLibro(Integer idLibro);
	public List<Valoracion> obtenerValoracionPorUsuario(Integer idUsuario, Integer idLibro);
}
