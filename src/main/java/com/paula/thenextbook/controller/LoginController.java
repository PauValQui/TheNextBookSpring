package com.paula.thenextbook.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.paula.thenextbook.model.Usuario;
import com.paula.thenextbook.service.IUsuarioService;

import jakarta.validation.Valid;


@Controller
public class LoginController {
	
	@Autowired
	private IUsuarioService serviceUsuario;
	
	@GetMapping(value = {"/login"})
    public String login(){
        return "auth/login";
    }

    @GetMapping(value = {"/register"})
    public String register(Model model){
        model.addAttribute("user", new Usuario());
        return "auth/register";
    }

    @PostMapping(value = {"/register"})
    public String registerUser(Model model, @Valid Usuario user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("successMessage", "User registered successfully!");
            model.addAttribute("bindingResult", bindingResult);
            return "auth/register";
        }
        List<Object> userPresentObj = serviceUsuario.isUserPresent(user);
        if((Boolean) userPresentObj.get(0)){
            model.addAttribute("successMessage", userPresentObj.get(1));
            return "auth/register";
        }

        serviceUsuario.guardar(user);
        model.addAttribute("successMessage", "User registered successfully!");

        return "auth/login";
    }
}

