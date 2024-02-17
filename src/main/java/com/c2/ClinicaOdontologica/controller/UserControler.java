package com.c2.ClinicaOdontologica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControler {
    @GetMapping("/login")
    public String home(){
        return "<h1> Welcome </h1>";
    }
}
