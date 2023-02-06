package com.paula.thenextbook.service.db;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.paula.thenextbook.model.Usuario;
import com.paula.thenextbook.repository.UsuarioRepository;
import com.paula.thenextbook.service.IUsuarioService;

@Service
@Primary
public class UsuarioServiceJpa implements IUsuarioService, UserDetailsService{

	@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public void guardar(Usuario usuario) {
		 String encodedPassword = bCryptPasswordEncoder.encode(usuario.getPassword());
		 usuario.setPassword(encodedPassword);
//	     user.setRole(Role.USER);
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

	@Override
	public List<Object> isUserPresent(Usuario user) {
		boolean userExists = false;
		
        String message = null;
        Optional<Usuario> existingUserEmail = repo.findByEmail(user.getEmail());
        
        if(existingUserEmail.isPresent()){
            userExists = true;
            message = "Email Already Present!";
        }
        
        Optional<Usuario> existingUsername = repo.findByUsername(user.getUsername());
        
        if(existingUsername.isPresent()){
            userExists = true;
            message = "UserName Already Present!";
        }
        
        if (existingUserEmail.isPresent() && existingUsername.isPresent()) {
            message = "Email and UserName Both Already Present!";
        }
        
        System.out.println("existingUserEmail.isPresent() - "+existingUserEmail.isPresent()+"existingUsername.isPresent() - "+existingUsername.isPresent());
        
        return Arrays.asList(userExists, message);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 return repo.findByEmail(username).orElseThrow(
	                ()-> new UsernameNotFoundException(
	                        String.format("USER_NOT_FOUND", username)
	                ));
	}

}
