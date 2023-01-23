package com.paula.thenextbook.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String mostrarHeader(Model model) {
		
		List<String> listaSlider = new LinkedList<String>();
		
		listaSlider.add("Slider1.png");
		listaSlider.add("Slider2.png");
		listaSlider.add("Slider3.png");
		listaSlider.add("Slider4.png");
		listaSlider.add("Slider5.png");
		
		model.addAttribute("listaSlider", listaSlider);
		
		return "home";
	}
}
