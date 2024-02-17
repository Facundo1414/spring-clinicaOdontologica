package com.c2.ClinicaOdontologica.controller;

import com.c2.ClinicaOdontologica.entities.Paciente;
import com.c2.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.c2.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public  ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.registrarPaciente(paciente));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<Paciente>> listarTodos(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente> > buscarPacientePorID(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPaciente(id));
    }
    @GetMapping("/buscar/{email}")
    public ResponseEntity<Paciente> buscarPacientePorCorreo(@PathVariable String correo){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorCorreo(correo);
        if(pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado.get());
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping
    public ResponseEntity actualizarPaciente(@RequestBody Paciente paciente){
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPaciente(paciente.getId());
        if(pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
        }else{
            return ResponseEntity.badRequest().body("Paciente no encontrado");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteService.buscarPaciente(id);
        if (paciente.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok().body("Paciente Eliminado");
        }
        throw new ResourceNotFoundException("No existe ese ID en la base de datos: "+ id);
        //return ResponseEntity.badRequest().body("Paciente no encontrado");
    }

}
