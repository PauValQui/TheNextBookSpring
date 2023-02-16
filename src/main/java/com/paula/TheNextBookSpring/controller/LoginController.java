package com.paula.TheNextBookSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;



public class LoginController {
	
	
	@GetMapping(value = {"/login"})
    public String login(){
        return "login";
    }
}
