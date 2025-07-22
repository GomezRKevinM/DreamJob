package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.Empresa;
import com.talento_tech.BolsaEmpleo.Services.ServiceEmpresa;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final ServiceEmpresa serviceEmpresa;

    @Autowired
    public EmpresaController(ServiceEmpresa serviceEmpresa) {
        this.serviceEmpresa = serviceEmpresa;
    }

    @GetMapping("/empresa/{id}")
    public ResponseEntity<ResponseDto> getEmpresa(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body(new ResponseDto("El ID de la empresa debe ser un número positivo", null, 400));
        }
        ResponseDto response = serviceEmpresa.getEmpresaById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDto> getEmpresaByUser(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest()
                    .body(new ResponseDto("El ID del usuario debe ser un número positivo", null, 400));
        }
        ResponseDto response = serviceEmpresa.getEmpresaByUser(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> agregar(@RequestBody Empresa empresa) {
        ResponseDto response = serviceEmpresa.crearEmpresa(empresa);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/edit")
    public ResponseEntity<ResponseDto> editar(@RequestBody Empresa empresa) {
        ResponseDto response = serviceEmpresa.editarEmpresa(empresa);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<ResponseDto> eliminar(@PathVariable Long id) {
        ResponseDto response = serviceEmpresa.eliminarEmpresa(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> getList() {
        ResponseDto response = serviceEmpresa.getEmpresas();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/count")
    public ResponseEntity<ResponseDto> getCount() {
        ResponseDto response = serviceEmpresa.contar();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
