package com.c2.ClinicaOdontologica.controller;


import com.c2.ClinicaOdontologica.entities.Odontologo;
import com.c2.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.c2.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;


    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorID(@PathVariable Long id){
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }
    @GetMapping("/busqueda/{matricula}")
    public ResponseEntity<Optional<Odontologo>> buscarOdontologobyMatricula(@PathVariable String matricula){
        return ResponseEntity.ok(odontologoService.buscarPorMatricula(matricula));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.listarTodos());
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoBuscado  = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo Actualizado");
        }
        else {
            return ResponseEntity.badRequest().body("Odontologo no encontrado");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado  = odontologoService.buscarPorId(id);
        if (odontologoBuscado.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Odontologo Eliminado");
        }
        throw new ResourceNotFoundException("No existe ese ID en la base de datos: "+ id);
    }

    //
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> procesarErrorNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
