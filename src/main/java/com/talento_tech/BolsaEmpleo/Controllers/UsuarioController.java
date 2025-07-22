package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.Usuario;
import com.talento_tech.BolsaEmpleo.Entities.user_rol;
import com.talento_tech.BolsaEmpleo.Services.ServiceUsuario;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Tag(name = "Usuario Controller", description = "Controlador para manejar las operaciones de usuario")
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final ServiceUsuario serviceUsuario;

    @Autowired
    public UsuarioController(ServiceUsuario serviceUsuario) {
        this.serviceUsuario = serviceUsuario;
    }

    @PostMapping("/agregar")
    public ResponseEntity<ResponseDto> agregar(@RequestBody Usuario user) {
        ResponseDto response = serviceUsuario.agregarUsuario(user);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Obtener un usuario por ID")
    public ResponseEntity<ResponseDto> getUsuarioById(@PathVariable Long id) {
        ResponseDto response = serviceUsuario.getUserByID(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/profile/{id}")
    @Operation(summary = "Cargar el perfil de un usuario por ID")
    public ResponseEntity<Void> redirigirAPerfil(@PathVariable Long id) {
        return ResponseEntity.status(302)
                .header("Location", "http://localhost:8080/src/html/perfil.html?id=" + id)
                .build();
    }

    @PutMapping("/edit")
    @Operation(summary = "Editar un usuario existente")
    public ResponseEntity<ResponseDto> editar(@RequestBody Usuario usuario) {
        ResponseDto response = serviceUsuario.editarUsuario(usuario);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "Eliminar un usuario por ID")
    public ResponseEntity<ResponseDto> eliminar(@PathVariable Long id) {
        ResponseDto response = serviceUsuario.eliminarUsuario(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/list")
    @Operation(summary = "Listar todos los usuarios")
    public ResponseEntity<ResponseDto> listar() {
        ResponseDto response = serviceUsuario.getAllUsers();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/count")
    @Operation(summary = "Contar el número de usuarios")
    public ResponseEntity<Integer> contar() {
        return ResponseEntity.ok(serviceUsuario.contarUsuarios());
    }

    @PatchMapping("/login")
    @Operation(summary = "Iniciar sesión de usuario", description = "Permite a un usuario iniciar sesión proporcionando su nombre de usuario y contraseña.")
    public ResponseEntity<ResponseDto> login(@RequestBody Usuario user) {
        ResponseDto response;
        if (user.getEmail() != null && user.getPassword() != null) {
            response = serviceUsuario.login(user.getEmail(), user.getPassword());
        } else {
            response = serviceUsuario.login(user.getUsername(), user.getPassword());
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/logout")
    @Operation(summary = "Cerrar sesión de usuario", description = "Permite a un usuario cerrar sesión.")
    public ResponseEntity<ResponseDto> logout() {
        ResponseDto response = serviceUsuario.logout();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/user-session")
    @Operation(summary = "Obtener información de la sesión de usuario")
    public ResponseEntity<ResponseDto> getUserSessionInfo() {
        ResponseDto response = serviceUsuario.getUserSession();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/roles")
    @Operation(summary = "Listar roles disponibles")
    public ResponseEntity<ResponseDto> getRoles() {
        List<user_rol> roles = Arrays.asList(user_rol.values());
        return ResponseEntity.ok(new ResponseDto("Roles disponibles", roles, 200));
    }

    @PatchMapping("/user-rol")
    @Operation(summary = "Actualizar el rol de un usuario")
    public ResponseEntity<ResponseDto> updateRol(@RequestBody Usuario usuario) {
        ResponseDto response = serviceUsuario.actualizarRol(usuario);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
