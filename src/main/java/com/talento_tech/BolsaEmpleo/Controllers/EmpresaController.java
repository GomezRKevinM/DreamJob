package com.talento_tech.BolsaEmpleo.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.talento_tech.BolsaEmpleo.Entities.Empresa;
import com.talento_tech.BolsaEmpleo.Services.ServiceEmpresa;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

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
    public ResponseEntity<ResponseDto> getEmpresa(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID de la empresa debe ser un número positivo.");
        }
        ResponseDto respuesta = serviceEmpresa.getEmpresaById(id);
        
       return ResponseEntity.status(respuesta.getStatus()).body(respuesta);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> agregar(@RequestBody Empresa entity) {
        Empresa saved;
        try {
            saved = Empresa.class.cast(serviceEmpresa.crearEmpresa(entity).getData());
            if(saved != null) {
                return ResponseEntity.status(201).body(new ResponseDto("Empresa creada exitosamente", saved, 201));
            } else {
                return ResponseEntity.status(500).body(new ResponseDto("Error al crear la empresa", null, 500));
            }
        } catch (SQLException e) {
            return ResponseEntity.status(500).body(new ResponseDto("Error al crear la empresa", e, 500));
        }
    }
    
    

}
