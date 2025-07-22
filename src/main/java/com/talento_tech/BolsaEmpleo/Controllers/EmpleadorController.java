package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.Empleador;
import com.talento_tech.BolsaEmpleo.Services.ServiceEmpleador;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleadores")
public class EmpleadorController {

    private final ServiceEmpleador serviceEmpleador;

    @Autowired
    public EmpleadorController(ServiceEmpleador serviceEmpleador) {
        this.serviceEmpleador = serviceEmpleador;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getEmpleadorById(@PathVariable Long id) {
        ResponseDto response = serviceEmpleador.getEmpleador(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDto> getEmpleadorByUser(@PathVariable Long id) {
        ResponseDto response = serviceEmpleador.getEmpleadorByUser(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> getEmpleadores() {
        ResponseDto response = serviceEmpleador.getEmpleadores();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> createEmpleador(@RequestBody Empleador entity) {
        ResponseDto response = serviceEmpleador.createEmpleador(entity);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/remove/{empleadorId}")
    public ResponseEntity<ResponseDto> deleteEmpleador(@PathVariable("empleadorId") Long empleadorId) {
        ResponseDto response = serviceEmpleador.deleteEmpleador(empleadorId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
