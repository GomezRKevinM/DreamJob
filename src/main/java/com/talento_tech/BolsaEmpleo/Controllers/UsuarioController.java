package com.talento_tech.BolsaEmpleo.Controllers;


import java.sql.*;
import java.util.ArrayList;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.talento_tech.BolsaEmpleo.Entities.Usuario;
import com.talento_tech.BolsaEmpleo.Services.ServiceUsuario;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

import jakarta.servlet.http.HttpServletRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
    @RequestMapping("/users")
@Tag(name = "Usuario Controller", description = "Controlador para manejar las operaciones de usuario")
public class UsuarioController {
    ServiceUsuario serviceUsuario;

    public UsuarioController() throws SQLException {
        serviceUsuario = new ServiceUsuario();
    }

    @PostMapping("/add")
    @Operation(summary = "Agregar un nuevo usuario")    
    public void agregar(@RequestBody Usuario usuario) {
        serviceUsuario.agregarUsuario(usuario);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    @Operation(summary = "Obtener un usuario por ID")
    @CrossOrigin(origins = "*") // Permitir solicitudes desde cualquier origen
    public Usuario getUsuarioById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del usuario debe ser un número positivo.");
        }
        // Aquí debería ir la lógica para obtener el usuario de la base de datos
        return new Usuario(); // Retornar un usuario ficticio por ahora
    }

    @GetMapping("/profile/{id}")
    @ResponseBody
    @Operation(summary = "Cargar el perfil de un usuario por ID")
    @CrossOrigin(origins = "*") // Permitir solicitudes desde cualquier origen
    public ResponseEntity<Void> redirigirAPerfil(@PathVariable Long id) {
        return ResponseEntity.status(302)
                .header("Location", "http://localhost:8080/src/html/perfil.html?id=" + id)
                .build();
    }

    @PutMapping("/edit")
    @Operation(summary = "Editar un usuario existente")
    public void editar(@RequestBody Usuario usuario) {
        serviceUsuario.editarUsuario(usuario);
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "Eliminar un usuario por ID")  
    public void eliminar(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del usuario debe ser un número positivo.");
        }
        serviceUsuario.eliminarUsuario(id);
    }

    @GetMapping("/list")
    @ResponseBody
    @Operation(summary = "Listar todos los usuarios")
    @CrossOrigin(origins = "*") // Permitir solicitudes desde cualquier origen
    public ResponseEntity<ResponseDto> listar(HttpServletRequest request){
        ResponseDto response = serviceUsuario.getAllUsers(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/count")
    @ResponseBody
    @Operation(summary = "Contar el número de usuarios")
    public Integer contar(HttpServletRequest request){
        return serviceUsuario.contarUsuarios(request);
    }

    @PostMapping("/login")
    @ResponseBody
    @Operation(summary = "Iniciar sesión de usuario", description = "Permite a un usuario iniciar sesión proporcionando su nombre de usuario y contraseña.")
    @CrossOrigin(origins = "*") // Permitir solicitudes desde cualquier origen
    public ResponseEntity<ResponseDto> login(@RequestBody Usuario user) {
        ResponseDto response = null;
        if(user.getEmail() != null && user.getPassword() != null){
            response = serviceUsuario.login(user.getEmail(), user.getPassword());
        }else{
            response = serviceUsuario.login(user.getUsername(), user.getPassword());
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/logout")
    @ResponseBody
    @Operation(summary = "Cerrar sesión de usuario", description = "Permite a un usuario cerrar sesión.")
    @CrossOrigin(origins = "*") // Permitir solicitudes desde cualquier origen
    public void logout() {
        serviceUsuario.logout();
    }

    @GetMapping("/user-session")
    @ResponseBody
    @Operation(summary = "Obtener información de la sesión de usuario")
    public Usuario getUserSessionInfo() {
        return serviceUsuario.getUserSession();
    }
    
}
