package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Services.ServiceUsuario;
import com.talento_tech.BolsaEmpleo.Entities.Usuario;

import com.talento_tech.BolsaEmpleo.dto.LoginRequest;
import com.talento_tech.BolsaEmpleo.dto.LoginResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin               // durante desarrollo, permite peticiones desde el navegador
public class AuthController {

    private final ServiceUsuario service;

    public AuthController(ServiceUsuario service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {

        // Método que agregaste antes en ServiceUsuario
        Usuario u = service.validarCredenciales(req.getEmail(), req.getPassword());

        if (u == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        LoginResponse resp = new LoginResponse(
                u.getId(), u.getNombre(), u.getApellido(), u.getEmail());

        return ResponseEntity.ok(resp);
    }
}
