package com.c2.ClinicaOdontologica;


import com.c2.ClinicaOdontologica.entities.Odontologo;
import com.c2.ClinicaOdontologica.service.OdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo(){
        Odontologo odontologo = new Odontologo("safas2132","Facu","Allende");
        odontologoService.registrarOdontologo(odontologo);
        assertEquals(odontologo.getId(),1L);
    }
    @Test
    @Order(2)
    public void buscarOdontologo(){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(1L);
        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(3)
    public void listarTodos(){
        List<Odontologo> lista = odontologoService.listarTodos();
        assertEquals(1,lista.size());
    }

    @Test
    @Order(4)
    public void actualizarOdontologo(){
        odontologoService.actualizarOdontologo(new Odontologo(1L,"123sfasf21","Facu","Allende"));
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(1L);
        assertEquals("123sfasf21",odontologoBuscado.get().getMatricula());
    }

    @Test
    @Order(5)
    public void eliminarOdontologo(){
        odontologoService.eliminarOdontologo(1L);
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(1L);
        assertFalse(odontologoBuscado.isPresent());
    }

}
