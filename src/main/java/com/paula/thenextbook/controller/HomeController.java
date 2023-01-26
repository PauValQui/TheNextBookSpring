package com.paula.thenextbook.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.paula.thenextbook.model.Libro;
import com.paula.thenextbook.service.ILibroService;

@Controller
public class HomeController {
	
	@Autowired
	//@Qualifier("libroServiceJpa") otra forma de definir el Servicio que queremos que utilice
	private ILibroService serviceLibro;
	
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
		
		model.addAttribute("libros", Libros);
		
		return "home";
	}
}
