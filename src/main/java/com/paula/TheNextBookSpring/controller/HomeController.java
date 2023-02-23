package com.paula.TheNextBookSpring.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paula.TheNextBookSpring.model.Autor;
import com.paula.TheNextBookSpring.model.Libro;
import com.paula.TheNextBookSpring.model.Role;
import com.paula.TheNextBookSpring.model.Usuario;
import com.paula.TheNextBookSpring.model.Valoracion;
import com.paula.TheNextBookSpring.service.IAutorService;
import com.paula.TheNextBookSpring.service.ICategoriaService;
import com.paula.TheNextBookSpring.service.ILibroService;
import com.paula.TheNextBookSpring.service.IUsuarioService;



@Controller
public class HomeController {
	
	@Autowired
	//@Qualifier("libroServiceJpa") otra forma de definir el Servicio que queremos que utilice
	private ILibroService serviceLibro;
	
	@Autowired
	private IAutorService serviceautor;
	
	@Autowired
	private ICategoriaService serviceCategoria;
	
	@Autowired
	private IUsuarioService serviceusuario;
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		UsuarioLogeado(model);
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
	
	public void UsuarioLogeado(Model model) {
		Usuario usuario = new Usuario();
		
		if(serviceusuario.obtenerUsuarioPorEstatus().isPresent()) {
			usuario = serviceusuario.obtenerUsuarioPorEstatus().get();
		}
		
		model.addAttribute("usuario", usuario);
		
	}
	
	@GetMapping(value = {"/logout"})
	public String logout(Usuario usuario) {
		
		usuario = serviceusuario.obtenerUsuarioPorEstatus().get();
		
		serviceusuario.bloquear(usuario.getId());
		
		return "redirect:/";
	}
	
	@GetMapping(value = {"/login"})
    public String login(Model model, Usuario usuario){
		UsuarioLogeado(model);
        return "login";
    }
	
	@PostMapping(value = {"/login"})
	public String logearse(Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		String contraseña = "1111111111111111111111111111111111";
		String message = null;
		
		if (serviceusuario.obtenerUsuarioPorUsername(usuario.getUsername()).isPresent()) {
			
			contraseña = usuario.getPassword();
			
			usuario = serviceusuario.obtenerUsuarioPorUsername(usuario.getUsername()).get();
			
			
			if(contraseña.equals(usuario.getPassword()) && serviceusuario.obtenerUsuarioPorEmail(usuario.getEmail()).isPresent()) {
				
				serviceusuario.activar(usuario.getId());
				
				return "redirect:/";
			}
			else{
				message = "La contraseña no es correcta";
				attributes.addFlashAttribute("msg", message);
				return "redirect:login";
			}
		}else{
			message = "El usuario no se encuentra";
			attributes.addFlashAttribute("msg", message);
			return "redirect:login";
		}

	}
	
	@GetMapping(value = {"/registro"})
    public String registro(Model model){
		UsuarioLogeado(model);
		model.addAttribute("Usuario", new Usuario());
		
        return "registro";
    }
	
	@PostMapping("/save")
	public String guardarregistro(Usuario registro) {
		
		Date fecha = new Date();
		
		registro.setFechaRegistro(fecha);
		
		Role role = new Role();
		role.setId(2);
		role.setRole("USER");
		
		registro.setRole(role); 
		
		serviceusuario.guardar(registro);
		
		return "redirect:login";
	}
	
	@GetMapping(value = {"/search/{busqueda}"})
    public String search(@PathVariable("busqueda") String busqueda, Model model){
		UsuarioLogeado(model);
		
		List<Libro> Libros = serviceLibro.obtenerLibros();
		List<Libro> listaLibros= new LinkedList<Libro>();
		List<Autor> listaAutores = serviceautor.obtenerAutores();
		
		
		if(busqueda.equals("libros")){	
			model.addAttribute("libros", Libros);
			
		}
		
		if(busqueda.equals("autores")){
			model.addAttribute("autores", listaAutores);
		}
		
		if(serviceautor.obtenerAutorPorNombre(busqueda) != null) {
			for (Iterator<Libro> iterator = Libros.iterator(); iterator.hasNext();) {
				Libro libro = (Libro) iterator.next();
				
				if(libro.getAutor().getNombre().equals(busqueda)) {
					listaLibros.add(libro);
				}
				
			}
			model.addAttribute("libros", listaLibros);
		}
		
		if(serviceCategoria.obtenerCategoriaPorTipo(busqueda) != null) {
			for (Iterator<Libro> iterator = Libros.iterator(); iterator.hasNext();) {
				Libro libro = (Libro) iterator.next();
				
				if(libro.getCategoria().getTipo().equals(busqueda)) {
					listaLibros.add(libro);
				}
				
			}
			model.addAttribute("libros", listaLibros);
		}
		
        return "search";
    }
	
	@GetMapping(value = {"/view/{id}"})
    public String vistaLibro(@PathVariable("id") int idLibro, Model model){
		UsuarioLogeado(model);
		
		Libro libro = serviceLibro.obtenerLibroPorId(idLibro);
		
		model.addAttribute("libro", libro);
		
        return "view";
    }
	
	@GetMapping("/saveValuations/{id}")
	public String mostrarFormularioValoracion(@PathVariable("id") int idLibro, Model model) {
		UsuarioLogeado(model);
		
		return "saveValuations";
	}
	
	@PostMapping("/saveValuations")
	public String guardarValoracion(Valoracion valoracion) {
		
		return "view/{idLibro}";
	}
}
