package com.talento_tech.BolsaEmpleo.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.talento_tech.BolsaEmpleo.Entities.Empresa;
import com.talento_tech.BolsaEmpleo.Services.ServiceEmpresa;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    ServiceEmpresa serviceEmpresa;
    

    public EmpresaController() {
        serviceEmpresa = new ServiceEmpresa();
    }

    @GetMapping("/empresa/{id}")
    public Empresa getEmpresa(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID de la empresa debe ser un número positivo.");
        }
        return serviceEmpresa.getEmpresaById(id);
    }
    

}
