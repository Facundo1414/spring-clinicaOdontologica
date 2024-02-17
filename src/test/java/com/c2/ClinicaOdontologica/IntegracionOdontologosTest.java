package com.c2.ClinicaOdontologica;


import com.c2.ClinicaOdontologica.entities.Odontologo;
import com.c2.ClinicaOdontologica.service.OdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionOdontologosTest {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void registrarOdontologo() throws Exception{
        Odontologo odontologoPayload = new Odontologo("123","Facu","Allende");
        Odontologo odontologoResponse = new Odontologo(1L, "123","Facu","Allende");

        String odontologoJson = objectMapper.writeValueAsString(odontologoPayload);
        String odontologoResp = objectMapper.writeValueAsString(odontologoResponse);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/odontologo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(odontologoJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertEquals(odontologoResp, result.getResponse().getContentAsString());
    }

}
