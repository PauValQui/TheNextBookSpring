package com.paula.thenextbook.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paula.thenextbook.model.Autor;
import com.paula.thenextbook.model.Libro;
import com.paula.thenextbook.model.Usuario;
import com.paula.thenextbook.service.IAutorService;
import com.paula.thenextbook.service.ILibroService;
import com.paula.thenextbook.service.IUsuarioService;

@Controller
public class HomeController {
	
	@Autowired
	//@Qualifier("libroServiceJpa") otra forma de definir el Servicio que queremos que utilice
	private ILibroService serviceLibro;
	
	@Autowired
	private IAutorService serviceautor;
	
	@Autowired
	private IUsuarioService serviceUsuario;
	
	@GetMapping("/")
	public String mostrarHeader(Model model) {
		//Slider
		List<String> listaSlider = new LinkedList<String>();
		
		listaSlider.add("Slider1.png");
		listaSlider.add("Slider2.png");
		listaSlider.add("Slider3.png");
		listaSlider.add("Slider4.png");
		listaSlider.add("Slider5.png");
		
		model.addAttribute("listaSlider", listaSlider);
		
		//Lista para Tarjetas
		List<Libro> Libros = serviceLibro.obtenerLibros();
		List<Autor> autores= serviceautor.obtenerAutores();
		
		model.addAttribute("libros", Libros);
		model.addAttribute("autores", autores);
		
		return "home";
	}
	
	@GetMapping("/login")
	public String inicioSesion() {
		return "login";
	}
	
	@GetMapping("/registro")
	public String mostrarFormularioRegistro(Model model) {
		
		model.addAttribute("userForm", new Usuario());
		
		return "registro";
	}
	
	@PostMapping("/procesar_registro")
	public String procesarRegistro(Usuario usuario) {
		
		/*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(usuario.getPassword());
	    usuario.setPassword(encodedPassword);
	     
	    userRepo.save(usuario);
	     
	    return "register_success";*/
		
		return "confirmar_registro";
	}
}
