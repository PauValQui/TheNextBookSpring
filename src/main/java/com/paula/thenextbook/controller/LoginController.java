package com.paula.thenextbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paula.thenextbook.service.IUsuarioService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private IUsuarioService serviceUsuario;
	
	@GetMapping("/")
	public String inicioSesion() {
		return "login";
	}
	
	@PostMapping("/registro")
	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("email") String email,
			@RequestParam("username") String username, @RequestParam("password") String password, 
			@RequestParam("password2") String password2) {
		
		
		
		return "registro";
	}
}
