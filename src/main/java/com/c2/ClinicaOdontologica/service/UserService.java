package com.c2.ClinicaOdontologica.service;

import com.c2.ClinicaOdontologica.entities.Usuario;
import com.c2.ClinicaOdontologica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<Usuario> usuarioBuscado = userRepository.findByEmail(email);
//        if (usuarioBuscado.isPresent()){
//            return usuarioBuscado.get();
//        }
//        return new UsernameNotFoundException("User email not found");


        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User email not found"));
    }
}
