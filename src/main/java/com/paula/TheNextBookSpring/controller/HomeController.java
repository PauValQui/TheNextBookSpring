package com.paula.TheNextBookSpring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paula.TheNextBookSpring.model.Autor;
import com.paula.TheNextBookSpring.model.Categoria;
import com.paula.TheNextBookSpring.model.Libro;
import com.paula.TheNextBookSpring.model.Role;
import com.paula.TheNextBookSpring.model.Usuario;
import com.paula.TheNextBookSpring.model.Valoracion;
import com.paula.TheNextBookSpring.service.IAutorService;
import com.paula.TheNextBookSpring.service.ICategoriaService;
import com.paula.TheNextBookSpring.service.ILibroService;
import com.paula.TheNextBookSpring.service.IUsuarioService;
import com.paula.TheNextBookSpring.service.IValoracionService;



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
	
	@Autowired
	private IValoracionService servicevaloracion;
	
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
				attributes.addFlashAttribute("msgC", message);
				return "redirect:login";
			}
		}else{
			message = "El usuario no se encuentra";
			attributes.addFlashAttribute("msgU", message);
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
	public String guardarregistro(Usuario registro, String password_confirmation, RedirectAttributes attributes) {
		String message = null;
		String ruta = "redirect:registro";
		
		
		if(!registro.getUsername().isEmpty()) {
			if(serviceusuario.obtenerUsuarioPorUsername(registro.getUsername()).isEmpty()) {
				if(!registro.getNombre().isEmpty()) {
					if(!registro.getEmail().isEmpty()) {
						if(serviceusuario.obtenerUsuarioPorEmail(registro.getEmail()).isEmpty()){
							if(registro.getEmail().contains("@") && registro.getEmail().contains(".")){
								if(!registro.getPassword().isEmpty() || !password_confirmation.isEmpty()) {
										if(registro.getPassword().equals(password_confirmation) && !registro.getPassword().isEmpty() || !registro.getPassword().isEmpty()) {
			
											Date fecha = new Date();
						
											registro.setFechaRegistro(fecha);
						
											Role role = new Role(); role.setId(2); role.setRole("USER");
						
											registro.setRole(role); serviceusuario.guardar(registro);
						
											ruta = "redirect:login";
			
										}else { 
											message = "Las contraseñas no son iguales";
											}
									}else {
										message = "Tienes que introducir las dos contraseñas.";
										}
							}else { 
								message = "El email no esta bien escrito";
								}
						}else { 
							message = "El email ya esta registrado";}
					}else {
						message = "Tienes que introducir un email.";
					}
				}else {
					message = "Tienes que introducir tu nombre.";
				}
			}else { 
				message = "El usuario ya esta registrado";
			}
		}else {
			message = "Tienes que introducir un nombre de usuario";
		}
		
		attributes.addFlashAttribute("msg", message); 
		return ruta; 
			 
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
		
		List<Valoracion> valoracion = servicevaloracion.obtenerValoracionPorLibro(idLibro);
		
		model.addAttribute("libro", libro);
		model.addAttribute("valoraciones", valoracion);
		
        return "view";
    }
	
	@GetMapping("/saveValuations/{idLibro}")
	public String mostrarFormularioValoracion(@PathVariable("idLibro") Integer idLibro, Model model) {
		UsuarioLogeado(model);
		
		model.addAttribute("idLibro", idLibro);
		model.addAttribute("valoracion", new Valoracion());
		
		return "saveValuations";
	}
	
	@PostMapping(value = "/saveValuations")
	public String guardarValoracion(Valoracion valoracion, String idLibro, RedirectAttributes attributes, Model model) {
		Usuario usuario = new Usuario();
		
		if(serviceusuario.obtenerUsuarioPorEstatus().isPresent()) {
			usuario = serviceusuario.obtenerUsuarioPorEstatus().get();
		}
		
		String msg = null;
		boolean cont = false;
		Integer libroId = Integer.parseInt(idLibro);
		valoracion.setUsuario(usuario);
		
		Libro libro = serviceLibro.obtenerLibroPorId(libroId);
		valoracion.setLibro(libro);
		
		if(!servicevaloracion.obtenerValoracionPorUsuario(valoracion.getUsuario().getId(), libroId).isEmpty()) {
			List<Valoracion> valoracionesUsuario = servicevaloracion.obtenerValoracionPorUsuario(valoracion.getUsuario().getId(), libroId);
			
			for (Valoracion user : valoracionesUsuario) {
					if(user.getLibro().getId() == valoracion.getLibro().getId()) {
						cont = true;
					}
			}
		}
		
		if(servicevaloracion.obtenerValoracionPorUsuario(valoracion.getUsuario().getId(), libroId).isEmpty() && cont == false) {
			if(valoracion.getTitulo().isEmpty() || valoracion.getComentario().isEmpty()) {
				msg = "Rellena todos las casillas del formulario";
				attributes.addFlashAttribute("msg", msg); 
				return "redirect:saveValuations";
			}else {
				servicevaloracion.guardar(valoracion);
				return "redirect:view/" + idLibro;
			}
		}else {
			return "redirect:view/" + idLibro;
		}
		
	}
	
	@GetMapping("/introducir/{valor}")
	public String Introducir(@PathVariable("valor") String valor,Model model) {
		UsuarioLogeado(model);
		List<Autor> autores = serviceautor.obtenerAutores();
		List<Categoria> categorias = serviceCategoria.obtenerCategoria();
		
		if (valor.equals("libros" )|| valor.equals("autor") || valor.equals("categoria")) {
			model.addAttribute("valor", valor);

			model.addAttribute("libro", new Libro());
			model.addAttribute("autor", new Autor());
			model.addAttribute("categoria", new Categoria());
			model.addAttribute("autores", autores);
			model.addAttribute("categorias", categorias);
		}
		
		return "introducir";
	}
	
	@PostMapping(value = "/guardar/libro")
	public String Guardar_Libro(Libro libro, RedirectAttributes attributes) {
		String msg= null;
		String valor = "libros";
		
		System.out.print(serviceLibro.findByTitulo(libro.getTitulo()).getTitulo());
		
		if(!libro.getTitulo().isEmpty()) {
			if(serviceLibro.findByTitulo(libro.getTitulo()).getTitulo() == null){
				System.out.print(libro.getTitulo());
				if(!libro.getSinopsis().isEmpty()) {
					if(!libro.getFoto().isEmpty()) {
						if(libro.getPrecio() != 0) {
							serviceLibro.guardar(libro);
						}else {
							msg="Introduce un precio distinto de 0.";
						}
					}else {
						msg="Introduce el nombre de la foto.";
					}
				}else {
					msg="Introduce sipnosis.";
				}
			}else {
				msg="Este titulo ya está introducido."; 
			}
		}else {
			msg="Introduce un titulo";
		}
		
		attributes.addFlashAttribute("msg", msg);
		return "redirect:/introducir/" + valor ;
		
	}
	
	@PostMapping(value = "/guardar/autor")
	public String Guardar_Autor(Autor autor, RedirectAttributes attributes) {
		String msg = null;
		String valor = "autor";
		if(!autor.getFoto().isEmpty()) {
			if(!autor.getNombre().isEmpty()) {
				if(serviceautor.obtenerAutorPorNombre(autor.getNombre()) == null) {
					serviceautor.guardar(autor);
				}else {
					msg = "Ese autor ya esta introducido.";
				}
			}else {
				msg="Introduce el nombre del autor.";
			}
		}else {
			msg="Introduce el nombre de la foto.";
		}
		
		attributes.addFlashAttribute("msg", msg);
		return "redirect:/introducir/" + valor;
		
	}
	
	@PostMapping(value = "/guardar/categoria")
	public String Guardar_Categoria(Categoria categoria, RedirectAttributes attributes) {
		String msg = null;
		String valor= "categoria";
		if(!categoria.getTipo().isEmpty()) {
			if(serviceCategoria.obtenerCategoriaPorTipo(categoria.getTipo()) == null) {
				serviceCategoria.guardar(categoria);
			}else {
				msg="Esta categoria ya esta guardada";
			}
		}else {
			msg="Introduce un tipo nuevo";
		}
		
		attributes.addFlashAttribute("msg", msg);
		return "redirect:/introducir/" + valor;
		
	}
	
	@GetMapping("/usuarios/{valor}")
	public String mostrarUsuarios(@PathVariable("valor") String valor, Model model) {
		UsuarioLogeado(model);
		
		List<Usuario> Users = new ArrayList<Usuario>();
		
		if(valor.equals("user")) {
			Users= serviceusuario.obtenerPorRole(2);
		}else if(valor.equals("admin")) {
			Users= serviceusuario.obtenerPorRole(1);
		}
		
		model.addAttribute("user", Users);
		model.addAttribute("valor", valor);
		
		return "viewUser";
	}
}




























