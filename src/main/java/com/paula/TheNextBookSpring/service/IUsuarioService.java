package com.paula.TheNextBookSpring.service;

import java.util.List;
import java.util.Optional;

import com.paula.TheNextBookSpring.model.Usuario;

public interface IUsuarioService{
	
	public void guardar(Usuario usuario);
	public void eliminar(Integer id);
	public long conteo();
	public List<Usuario> obtenerTodos();
	public Optional<Usuario> obtenerUsuarioPorId(Integer id);
	public Optional<Usuario> obtenerUsuarioPorUsername(String username);
	public Optional<Usuario> obtenerUsuarioPorEmail(String email);
	public Optional<Usuario> obtenerUsuarioPorEstatus();
	public void bloquear(int idUsuario);
	public void activar(int idUsuario);
}
