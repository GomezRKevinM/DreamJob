package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.ReferenciaPersonal;
import com.talento_tech.BolsaEmpleo.Services.ServicioRefPersonal;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ref")
public class ReferenciaPersonalController {
    ServicioRefPersonal service;

    public ReferenciaPersonalController() {
        service = new ServicioRefPersonal();
    }

    @GetMapping("/add")
    public ResponseEntity<ResponseDto> agregar(ReferenciaPersonal referencia){
        ResponseDto response = service.agregar(referencia);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editar(@RequestBody ReferenciaPersonal referencia){
        ResponseDto response = service.actualizar(referencia);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<ResponseDto> eliminar(@PathVariable Long id){
        ResponseDto response = service.eliminar(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable Long id){
        ResponseDto response = service.getReferencia(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<ResponseDto> getReferenciasByEmpleado(@PathVariable Long id){
        ResponseDto response = service.getReferenciasByEmpleado(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
