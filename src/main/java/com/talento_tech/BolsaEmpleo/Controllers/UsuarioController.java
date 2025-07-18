package com.talento_tech.BolsaEmpleo.Controllers;


import java.sql.*;
import java.util.ArrayList;


import com.talento_tech.BolsaEmpleo.Entities.user_rol;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.talento_tech.BolsaEmpleo.Entities.Usuario;
import com.talento_tech.BolsaEmpleo.Services.ServiceUsuario;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

import jakarta.servlet.http.HttpServletRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "Usuario Controller", description = "Controlador para manejar las operaciones de usuario")
@RestController
@RequestMapping("/users")
public class UsuarioController {
    ServiceUsuario serviceUsuario;

    public UsuarioController() throws SQLException {
        serviceUsuario = new ServiceUsuario();
    }

    @PostMapping("/agregar")
    public ResponseEntity<ResponseDto> agregar(@RequestBody Usuario user){
        ResponseDto response = serviceUsuario.agregarUsuario(user);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    @Operation(summary = "Obtener un usuario por ID")
    @CrossOrigin(origins = "*") // Permitir solicitudes desde cualquier origen
    public ResponseEntity<ResponseDto> getUsuarioById(@PathVariable Long id) {
        ResponseDto response = serviceUsuario.getUserByID(id);
        return ResponseEntity.status(response.getStatus()).body(response);
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

    @PatchMapping("/login")
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
    public ResponseEntity<ResponseDto> logout() {
        ResponseDto response = serviceUsuario.logout();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/user-session")
    @ResponseBody
    @CrossOrigin(origins = "*") //Siempres olvidas esto.
    @Operation(summary = "Obtener información de la sesión de usuario")
    public ResponseEntity<ResponseDto> getUserSessionInfo() {
        ResponseDto response = serviceUsuario.getUserSession();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/roles")
    public ResponseDto getRoles(){
        ArrayList<user_rol> roles = new ArrayList<>();

        for(user_rol rol: user_rol.values()){
            roles.add(rol);
        }
        return new ResponseDto("Roles disponibles",roles,200);
    }

    @PatchMapping("/user-rol/")
    public ResponseDto updateRol(@RequestBody Usuario usuario) {
        String sql = "UPDATE usuarios SET rol = ?::user_role WHERE user_id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, usuario.getRol());
            ps.setLong(2, usuario.getId());

            int filas = ps.executeUpdate();

            if(filas > 0){
                ResponseDto usuarioCambiado = serviceUsuario.getUserByID(usuario.getId());
                return new ResponseDto("Rol actualizado exitosamente",usuarioCambiado.getData(),200);
            }else{
                return new ResponseDto("Rol no actualizado, no se encontro el usuario",null,404);
            }
        }catch (SQLException e){
            return new ResponseDto("Error al actualizar rol del usuario",e.getMessage(),500);
        }
    }
    
}
