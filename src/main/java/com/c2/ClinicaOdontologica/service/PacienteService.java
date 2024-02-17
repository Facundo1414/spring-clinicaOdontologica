package com.c2.ClinicaOdontologica.service;

import com.c2.ClinicaOdontologica.entities.Domicilio;
import com.c2.ClinicaOdontologica.entities.Paciente;
import com.c2.ClinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    //se generan manuales los metodos
   public Paciente registrarPaciente(Paciente paciente){
       return pacienteRepository.save(paciente);
   }
   public Paciente actualizarPaciente(Paciente paciente){
       return pacienteRepository.save(paciente);
   }
   public void eliminarPaciente(Long id){
       pacienteRepository.deleteById(id);
   }
   public Optional<Paciente> buscarPaciente(Long id){
       return pacienteRepository.findById(id);
   }
   public Optional<Paciente> buscarPorCorreo(String mail){
       return pacienteRepository.findByEmail(mail);
   }
   public List<Paciente> listarTodos (){
       return pacienteRepository.findAll();
   }


}
