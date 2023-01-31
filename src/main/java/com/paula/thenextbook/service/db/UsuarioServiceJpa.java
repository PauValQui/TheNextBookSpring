package com.paula.thenextbook.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.paula.thenextbook.model.Usuario;
import com.paula.thenextbook.repository.UsuarioRepository;
import com.paula.thenextbook.service.IUsuarioService;

@Service
@Primary
public class UsuarioServiceJpa implements IUsuarioService{

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public void guardar(Usuario usuario) {
		repo.save(usuario);
	}

	@Override
	public void obtenerUsuarioPorId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long conteo() {
		// TODO Auto-generated method stub
		return 0;
	}

}
