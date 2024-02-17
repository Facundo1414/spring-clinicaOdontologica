package com.c2.ClinicaOdontologica;

import com.c2.ClinicaOdontologica.entities.Domicilio;
import com.c2.ClinicaOdontologica.entities.Odontologo;
import com.c2.ClinicaOdontologica.entities.Paciente;
import com.c2.ClinicaOdontologica.entities.Turno;
import com.c2.ClinicaOdontologica.service.OdontologoService;
import com.c2.ClinicaOdontologica.service.PacienteService;
import com.c2.ClinicaOdontologica.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // esta configuracion desactiva la seguridad de  spring para el test
public class IntegracionTurnosTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc; // clase que usaremos para realizar los test

    public void agregarDatosIniciales(){
        Paciente paciente = new Paciente("Facu","Allende","21312", LocalDate.of(2023,10,27),new Domicilio("libertad",31231,"Cordoba","Cordoba"),"Facu@gmail.com");
        Odontologo odontologo = new Odontologo("MP10","Jose","Lopez");
        Turno turno = new Turno(paciente,odontologo,LocalDate.of(2020,03,13));
        pacienteService.registrarPaciente(paciente);
        odontologoService.registrarOdontologo(odontologo);
        turnoService.guardarTurno(turno);
    }

    @Test
    public void listarTurnosTest() throws Exception{
        agregarDatosIniciales();
        MvcResult result =
                mockMvc
                    .perform(MockMvcRequestBuilders
                        .get("/turnos")
                        .accept(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                    .andReturn();

        assertFalse(result.getResponse().getContentAsString().isEmpty()); // si tdo esta correcto no deberia estar vacio
    }



}
