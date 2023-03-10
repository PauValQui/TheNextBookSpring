package com.paula.thenextbook.service;

import java.util.List;

import com.paula.thenextbook.model.Autor;

public interface IAutorService {

	public void guardar(Autor autor);
	public void eliminar(Integer id);
	public long conteo();
	public Autor obtenerAutorPorId(Integer id);
	public Autor obtenerAutorPorNombre(String nombre);
	public List<Autor> obtenerAutores();
}
