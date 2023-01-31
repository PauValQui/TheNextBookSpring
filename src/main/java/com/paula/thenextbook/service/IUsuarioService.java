package com.paula.thenextbook.service;

import com.paula.thenextbook.model.Usuario;

public interface IUsuarioService {
	
	public void guardar(Usuario usuario);
	public void obtenerUsuarioPorId(Integer id);
	public void eliminar(Integer id);
	public long conteo();
	
}
