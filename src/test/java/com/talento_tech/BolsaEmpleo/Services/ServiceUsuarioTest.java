package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Entities.Usuario;
import com.talento_tech.BolsaEmpleo.Repositories.UsuarioRepository;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

public class ServiceUsuarioTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ServiceUsuario serviceUsuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        serviceUsuario = new ServiceUsuario(usuarioRepository, null, null, null);
    }

    @Test
    void actualizarRol_deberiaActualizarExitosamente() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setRol("empleado");

        when(usuarioRepository.updateRol(1L, "empleado")).thenReturn(1);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Act
        ResponseDto response = serviceUsuario.actualizarRol(usuario);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals("Usuario encontrado", response.getMessage());
        assertNotNull(response.getData());
    }

    @Test
    void actualizarRol_usuarioNoEncontrado() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(2L);
        usuario.setRol("empresa");

        when(usuarioRepository.updateRol(2L, "empresa")).thenReturn(0);

        // Act
        ResponseDto response = serviceUsuario.actualizarRol(usuario);

        // Assert
        assertEquals(404, response.getStatus());
        assertEquals("Rol no actualizado, no se encontró el usuario", response.getMessage());
    }

    @Test
    void actualizarRol_deberiaManejarExcepcion() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId(3L);
        usuario.setRol("empleador");

        when(usuarioRepository.updateRol(3L, "empleador"))
                .thenThrow(new RuntimeException("Error simulado"));

        // Act
        ResponseDto response = serviceUsuario.actualizarRol(usuario);

        // Assert
        assertEquals(500, response.getStatus());
        assertEquals("Error al actualizar rol del usuario", response.getMessage());
        assertTrue(response.getData() instanceof String);
    }
}
