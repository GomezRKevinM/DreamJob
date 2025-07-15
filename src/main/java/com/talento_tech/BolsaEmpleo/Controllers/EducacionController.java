package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.Educacion;
import com.talento_tech.BolsaEmpleo.Services.ServiceEducacion;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/education")
public class EducacionController {
    ServiceEducacion serviceEducacion;

    public EducacionController(){
        serviceEducacion = new ServiceEducacion();
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> agregar(@RequestBody Educacion educacion){
        ResponseDto response = serviceEducacion.agregar(educacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> buscar(@PathVariable Long id){
        ResponseDto response = serviceEducacion.getEducacion(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDto> buscarUser(@PathVariable Long id){
        ResponseDto response = serviceEducacion.getEducacionByEmpleadoId(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editar(@RequestBody Educacion educacion){
        ResponseDto response = serviceEducacion.actualizar(educacion);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<ResponseDto> eliminar(@PathVariable Long id){
        ResponseDto response = serviceEducacion.eliminar(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
