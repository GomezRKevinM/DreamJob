package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.Educacion;
import com.talento_tech.BolsaEmpleo.Services.ServiceEducacion;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/education")
@CrossOrigin(origins = "*")
public class EducacionController {

    private final ServiceEducacion serviceEducacion;

    @Autowired
    public EducacionController(ServiceEducacion serviceEducacion) {
        this.serviceEducacion = serviceEducacion;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> agregar(@RequestBody Educacion educacion) {
        ResponseDto response = serviceEducacion.agregar(educacion);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> buscar(@PathVariable Long id) {
        ResponseDto response = serviceEducacion.getEducacion(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDto> buscarPorEmpleado(@PathVariable Long id) {
        ResponseDto response = serviceEducacion.getEducacionByEmpleadoId(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editar(@RequestBody Educacion educacion) {
        ResponseDto response = serviceEducacion.actualizar(educacion);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<ResponseDto> eliminar(@PathVariable Long id) {
        ResponseDto response = serviceEducacion.eliminar(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
