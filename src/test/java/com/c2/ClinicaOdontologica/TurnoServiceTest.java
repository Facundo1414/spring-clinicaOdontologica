package com.c2.ClinicaOdontologica;

import com.c2.ClinicaOdontologica.dto.TurnoDTO;
import com.c2.ClinicaOdontologica.entities.Domicilio;
import com.c2.ClinicaOdontologica.entities.Odontologo;
import com.c2.ClinicaOdontologica.entities.Paciente;
import com.c2.ClinicaOdontologica.entities.Turno;
import com.c2.ClinicaOdontologica.repository.PacienteRepository;
import com.c2.ClinicaOdontologica.service.OdontologoService;
import com.c2.ClinicaOdontologica.service.PacienteService;
import com.c2.ClinicaOdontologica.service.TurnoService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarTurno(){
        Paciente paciente = new Paciente("Facu","Allende","21312", LocalDate.of(2023,10,27),new Domicilio("libertad",31231,"Cordoba","Cordoba"),"Facu@gmail.com");
        Odontologo odontologo = new Odontologo("MP10","Jose","Lopez");
        Turno turno = new Turno(paciente,odontologo,LocalDate.of(2020,03,13));
        pacienteService.registrarPaciente(paciente);
        odontologoService.registrarOdontologo(odontologo);
        turnoService.guardarTurno(turno);
        Optional<TurnoDTO> turnodto = turnoService.buscarById(turno.getId());
        Assertions.assertFalse(turnodto.isEmpty());
    }

    @Test
    @Order(2)
    public void listarTodos(){
        List<TurnoDTO> listarTodos = turnoService.buscarTodos();
        Assertions.assertEquals(1,listarTodos.size());
    }

    @Test
    @Order(3)
    public void findByid(){
        Optional<TurnoDTO> turnoDTO = turnoService.buscarById(1L);
        Assertions.assertFalse(turnoDTO.isEmpty());
    }


    @Test
    @Order(5)
    public void delete(){
        turnoService.eliminarTurno(1L);
        Optional<TurnoDTO> turnodto = turnoService.buscarById(1L);
        Assertions.assertFalse(turnodto.isPresent());
    }

}
