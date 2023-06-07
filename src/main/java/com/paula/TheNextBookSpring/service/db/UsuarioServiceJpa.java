package com.paula.TheNextBookSpring.service.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.paula.TheNextBookSpring.model.Usuario;
import com.paula.TheNextBookSpring.repository.UsuarioRepository;
import com.paula.TheNextBookSpring.service.IUsuarioService;

import jakarta.transaction.Transactional;

@Service
@Primary
public class UsuarioServiceJpa implements IUsuarioService{
	
	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public void guardar(Usuario usuario) {
//	     user.setRole(Role.USER);
		 repo.save(usuario);
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
	public List<Usuario> obtenerTodos() {
		return repo.findAll();
	}
	
	@Override
	public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
		return repo.findById(id);
		
	}
	
	@Override
	public Optional<Usuario> obtenerUsuarioPorUsername(String username) {
		
		return repo.findByUsername(username);
	}

	@Override
	public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
		return repo.findByEmail(email);
	}
	
	@Override
	public Optional<Usuario> obtenerUsuarioPorEstatus() {
		boolean estatus = true;
		return repo.findByEstatus(estatus);
	}
	
	@Transactional
	@Override
	public void bloquear(int idUsuario) {
		repo.lock(idUsuario);

	}

	@Transactional
	@Override
	public void activar(int idUsuario) {
		repo.unlock(idUsuario);
	}

	@Override
	public List<Usuario> obtenerPorRole(Integer id) {
		List<Usuario> AllUsers = repo.findAll();
		List<Usuario> Users = new ArrayList<Usuario>();
		
		for (Usuario usuario : AllUsers) {
			if(usuario.getRole().getId() == id) {
				Users.add(usuario);
			}
		}
		
		return Users;
	}

	

}

