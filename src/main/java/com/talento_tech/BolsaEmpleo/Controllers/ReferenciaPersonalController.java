package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.ReferenciaPersonal;
import com.talento_tech.BolsaEmpleo.Services.ServicioRefPersonal;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/referencias")
public class ReferenciaPersonalController {

    private final ServicioRefPersonal service;

    @Autowired
    public ReferenciaPersonalController(ServicioRefPersonal service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> agregar(@RequestBody ReferenciaPersonal referencia) {
        ResponseDto response = service.agregar(referencia);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> editar(@PathVariable Long id, @RequestBody ReferenciaPersonal referencia) {
        referencia.setId(id); // Aseguramos que el ID venga también por URL
        ResponseDto response = service.actualizar(referencia);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> eliminar(@PathVariable Long id) {
        ResponseDto response = service.eliminar(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable Long id) {
        ResponseDto response = service.getReferencia(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<ResponseDto> getReferenciasByEmpleado(@PathVariable Long empleadoId) {
        ResponseDto response = service.getReferenciasByEmpleado(empleadoId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
