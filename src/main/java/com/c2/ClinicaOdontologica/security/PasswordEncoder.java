package com.c2.ClinicaOdontologica.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoder {

    @Bean // Le hacemos saber a spring que cuando necesite esta clase, lo puede obtener de aca...
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // este metodo lo requiere el SecurityConfiguration
}
