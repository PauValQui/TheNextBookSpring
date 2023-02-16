package com.paula.TheNextBookSpring.service;

import java.util.List;

import com.paula.TheNextBookSpring.model.Categoria;

public interface ICategoriaService {
	
	public void guardar(Categoria categoria);
	public void eliminar(Integer id);
	public long conteo();
	public Categoria obtenerCategoriaPorId(Integer id);
	public Categoria obtenerCategoriaPorTipo(String tipo);
	public List<Categoria> obtenerCategoria();
}
