package com.talento_tech.BolsaEmpleo.Controllers;


import java.sql.*;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

import com.talento_tech.BolsaEmpleo.Entities.Usuario;
import com.talento_tech.BolsaEmpleo.Services.ServiceUsuario;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    ServiceUsuario serviceUsuario;

    public UsuarioController() throws SQLException {
        serviceUsuario = new ServiceUsuario();
    }

    @PostMapping("/add")
    public void agregar(@RequestBody Usuario usuario) {
        serviceUsuario.agregarUsuario(usuario);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public Usuario getUsuarioById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del usuario debe ser un número positivo.");
        }
        // Aquí debería ir la lógica para obtener el usuario de la base de datos
        return new Usuario(); // Retornar un usuario ficticio por ahora
    }

    @PutMapping("/edit")
    public void editar(@RequestBody Usuario usuario) {
        serviceUsuario.editarUsuario(usuario);
    }

    @DeleteMapping("/del/{id}")
    public void eliminar(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del usuario debe ser un número positivo.");
        }
        serviceUsuario.eliminarUsuario(id);
    }

    @GetMapping("/list")
    @ResponseBody
    public ArrayList<Usuario> listar(HttpServletRequest request){
        return serviceUsuario.getAllUsers(request);
    }

    @GetMapping("/count")
    public Integer contar(HttpServletRequest request){
        if(listar(request) == null) {
            return 0; // Si la lista es nula, retornar 0
        }else{
            return listar(request).size();
        }// Retornar 0 por ahora como un valor ficticio
    }
}
