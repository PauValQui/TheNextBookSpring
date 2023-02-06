package com.paula.thenextbook.service;

import java.util.List;

import com.paula.thenextbook.model.Usuario;

public interface IUsuarioService {
	
	public void guardar(Usuario usuario);
	public void obtenerUsuarioPorId(Integer id);
	public List<Object> isUserPresent(Usuario user);
	public void eliminar(Integer id);
	public long conteo();
	
}
