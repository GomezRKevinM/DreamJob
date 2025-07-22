package com.talento_tech.BolsaEmpleo.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talento_tech.BolsaEmpleo.Entities.Usuario;
import com.talento_tech.BolsaEmpleo.Services.ServiceUsuario;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ServiceUsuario serviceUsuario;

    private Usuario usuario;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setRol("empleado");

        objectMapper = new ObjectMapper();
    }

    @Test
    void actualizarRol_deberiaResponder200() throws Exception {
        // Arrange
        ResponseDto responseDto = new ResponseDto("Rol actualizado exitosamente", usuario, 200);
        when(serviceUsuario.actualizarRol(any(Usuario.class))).thenReturn(responseDto);

        // Act + Assert
        mockMvc.perform(patch("/users/user-rol/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Rol actualizado exitosamente"))
                .andExpect(jsonPath("$.status").value(200));
    }

    @Test
    void actualizarRol_usuarioNoEncontrado_deberiaResponder404() throws Exception {
        // Arrange
        ResponseDto responseDto = new ResponseDto("Rol no actualizado, no se encontró el usuario", null, 404);
        when(serviceUsuario.actualizarRol(any(Usuario.class))).thenReturn(responseDto);

        // Act + Assert
        mockMvc.perform(patch("/users/user-rol/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value("Rol no actualizado, no se encontró el usuario"))
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void actualizarRol_errorInterno_deberiaResponder500() throws Exception {
        // Arrange
        ResponseDto responseDto = new ResponseDto("Error al actualizar rol del usuario", "error", 500);
        when(serviceUsuario.actualizarRol(any(Usuario.class))).thenReturn(responseDto);

        // Act + Assert
        mockMvc.perform(patch("/users/user-rol/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.mensaje").value("Error al actualizar rol del usuario"))
                .andExpect(jsonPath("$.status").value(500));
    }
}
