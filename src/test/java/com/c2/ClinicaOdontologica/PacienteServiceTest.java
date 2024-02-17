package com.c2.ClinicaOdontologica;


import com.c2.ClinicaOdontologica.entities.Domicilio;
import com.c2.ClinicaOdontologica.entities.Paciente;
import com.c2.ClinicaOdontologica.service.PacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPaciente(){
        Paciente paciente = new Paciente("Facu","Allende","21312", LocalDate.of(2023,10,27),new Domicilio("libertad",31231,"Cordoba","Cordoba"),"Facu@gmail.com");
        pacienteService.registrarPaciente(paciente);
        assertEquals(1L, paciente.getId());
    }
    @Test
    @Order(2)
    public void buscarPaciente(){
        Long id = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        assertNotNull(pacienteBuscado);
    }

    @Test
    @Order(3)
    public void buscarPacientesTest(){
        List<Paciente> pacientes= pacienteService.listarTodos();
        assertEquals(1,pacientes.size());
    }
    @Test
    @Order(4)
    public void actualizarPaciente(){
        Paciente pacienteActualizar= new Paciente(1L,"Facundito","Allende","0001", LocalDate.of(2023,10,27),new Domicilio("libertad",11111,"Cordoba","Cordoba"),"Facu@gmail.com");
        pacienteService.actualizarPaciente(pacienteActualizar);
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPaciente(1L);
        assertEquals("Facundito",pacienteBuscado.get().getNombre());
    }
    @Test
    @Order(5)
    public void eliminarPaciente(){
        Long idEliminar= 1L;
        pacienteService.eliminarPaciente(idEliminar);
        Optional<Paciente> pacienteEliminado= pacienteService.buscarPaciente(1L);
        assertFalse(pacienteEliminado.isPresent());

    }


}
