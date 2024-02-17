package com.c2.ClinicaOdontologica.controller;


import com.c2.ClinicaOdontologica.dto.TurnoDTO;
import com.c2.ClinicaOdontologica.entities.Odontologo;
import com.c2.ClinicaOdontologica.entities.Paciente;
import com.c2.ClinicaOdontologica.entities.Turno;
import com.c2.ClinicaOdontologica.exceptions.BadRequestException;
import com.c2.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.c2.ClinicaOdontologica.service.OdontologoService;
import com.c2.ClinicaOdontologica.service.PacienteService;
import com.c2.ClinicaOdontologica.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping()
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody Turno turno) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
            return ResponseEntity.ok((turnoService.guardarTurno(turno)));
        }
        throw new BadRequestException("No se puede registrar un nuevo turno. Revise los datos a ingresar");
        //return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TurnoDTO>> buscarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnobuscado = turnoService.buscarById(id);
        if (turnobuscado.isPresent()){
            return ResponseEntity.ok(turnobuscado);
        }
        throw new ResourceNotFoundException("No existe ese ID en la base de datos: "+ id);
        //return ResponseEntity.notFound().build();
    }
    //TODO
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turnoDTO) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarById(turnoDTO.getId());


        if (turnoBuscado.isPresent()){
            Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turnoDTO.getPacienteId());
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turnoDTO.getOdontologoId());
            if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
                Turno nuevoTurno = new Turno(turnoDTO.getId(), pacienteBuscado.get(), odontologoBuscado.get(), turnoDTO.getFechaTurno());
                turnoService.actualizarTurno(nuevoTurno);
            return ResponseEntity.ok("Turno actualizado");
            }
            else {
                return ResponseEntity.badRequest().body("No se pudo encontrar al paciente y/o al odontologo");

            }
        }
        throw new ResourceNotFoundException("Turno no encontrado. No existe ese ID en la base de datos: "+ turnoDTO.getId());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarById(id);
        if (turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno Eliminado");
        }
        throw new ResourceNotFoundException("No existe ese ID en la base de datos: "+ id);
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo encontrar el turno para su eliminacion: "+id);
    }
}
