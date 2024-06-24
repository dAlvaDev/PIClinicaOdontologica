package com.pidenis.appclinica;

import com.pidenis.appclinica.entity.Odontologo;
import com.pidenis.appclinica.service.OdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class OdontologoControllerTest {

    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void guardarOdontologo() throws Exception {
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();

        String payloadJson = writer.writeValueAsString(new Odontologo("A123", "David", "Blanco"));

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(payloadJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect((ResultMatcher) content().contentType("application/json"))
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}