package com.c2.ClinicaOdontologica.security;

import com.c2.ClinicaOdontologica.entities.*;
import com.c2.ClinicaOdontologica.repository.UserRepository;
import com.c2.ClinicaOdontologica.service.OdontologoService;
import com.c2.ClinicaOdontologica.service.PacienteService;
import com.c2.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private TurnoService turnoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordCifrado = passwordEncoder.encode("admin"); // asi se cifra la password
        //userRepository.save(new Usuario("Facu","admin","admin@gmail.com",passwordCifrado, AppUserRoles.ROLE_ADMIN));
    }
}
