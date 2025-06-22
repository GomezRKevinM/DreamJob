package com.talento_tech.BolsaEmpleo.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.talento_tech.BolsaEmpleo.Entities.Empresa;
import com.talento_tech.BolsaEmpleo.Services.ServiceEmpresa;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    ServiceEmpresa serviceEmpresa;
    

    public EmpresaController() {
        serviceEmpresa = new ServiceEmpresa();
    }

    @GetMapping("/empresa/{id}")
    public ResponseEntity<Empresa> getEmpresa(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID de la empresa debe ser un número positivo.");
        }
        return ResponseEntity.ok(serviceEmpresa.getEmpresaById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Object> postMethodName(@RequestBody Empresa entity) {
        Empresa saved;
        try {
            saved = serviceEmpresa.crearEmpresa(entity);
            if(saved != null) {
                ResponseEntity<Object> response = ResponseEntity.ok().body(saved);
                return response;
            } else {
                return ResponseEntity.badRequest().body("Error al crear la empresa");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear la empresa: " + e.getMessage());
        }
        return null;
    }
    
    

}
