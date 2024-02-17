package com.c2.ClinicaOdontologica.service;

import com.c2.ClinicaOdontologica.dto.TurnoDTO;
import com.c2.ClinicaOdontologica.entities.Domicilio;
import com.c2.ClinicaOdontologica.entities.Odontologo;
import com.c2.ClinicaOdontologica.entities.Paciente;
import com.c2.ClinicaOdontologica.entities.Turno;
import com.c2.ClinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public TurnoDTO guardarTurno(Turno turno){
        Turno turnoGuardado= turnoRepository.save(turno);
        return turnoATurnoDTO(turnoGuardado);
    }

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }

    public Turno actualizarTurno(Turno turno){
        return turnoRepository.save(turno);
    }

    public Optional<TurnoDTO> buscarById(Long id){
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }
        return Optional.empty();
    }

    public List<TurnoDTO> buscarTodos(){
        List<Turno> turnosEncontrados = turnoRepository.findAll();
        List<TurnoDTO> turnoDTOS = new ArrayList<>();
        for (Turno turnosEncontrado : turnosEncontrados) {
            turnoDTOS.add(turnoATurnoDTO(turnosEncontrado));
        }
        return turnoDTOS;
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
        TurnoDTO respuesta= new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setFechaTurno(turno.getFechaTurno());
        return  respuesta;
    }

//    private Turno dtoaTurno (TurnoDTO turnoDTO){
//        Turno nuevoTurno = new Turno();
//        Paciente paciente = new Paciente();
//        Odontologo odontologo = new Odontologo();
//        //
//        paciente.setId(turnoDTO.getPacienteId());
//        odontologo.setId(turnoDTO.getOdontologoId());
//        //
//        nuevoTurno.setId(turnoDTO.getId());
//        nuevoTurno.setFechaTurno(turnoDTO.getFechaTurno());
//        nuevoTurno.setPaciente(paciente);
//        nuevoTurno.setOdontologo(odontologo);
//        return nuevoTurno;
//    }


}
