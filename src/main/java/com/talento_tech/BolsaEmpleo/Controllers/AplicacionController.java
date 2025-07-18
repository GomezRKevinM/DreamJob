package com.talento_tech.BolsaEmpleo.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talento_tech.BolsaEmpleo.Entities.Aplicacion;
import com.talento_tech.BolsaEmpleo.Services.ServiceAplicacion;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/aplicacion")
public class AplicacionController {
    private ServiceAplicacion serviceAplicacion;

    public AplicacionController() {
        this.serviceAplicacion = new ServiceAplicacion();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/aplicar")    
    public ResponseEntity<ResponseDto> aplicar(@RequestBody Aplicacion aplicacion){
        ResponseDto response = serviceAplicacion.aplicar(aplicacion.getIdOfertaEmpleo(), aplicacion.getIdEmpleado());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/listar")    
    public ResponseEntity<ResponseDto> listar(@RequestParam Long idOfertaEmpleo){
        ResponseDto response = serviceAplicacion.getByOferta(idOfertaEmpleo);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")    
    public ResponseEntity<ResponseDto> obtener(@PathVariable Long id){
        ResponseDto response = serviceAplicacion.get(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @CrossOrigin(origins = "*")
    @PatchMapping("/update")    
    public ResponseEntity<ResponseDto> update(@RequestBody Aplicacion aplicacion){
        ResponseDto response = serviceAplicacion.update(aplicacion.getId(),aplicacion.getEstado(),aplicacion.getComentario());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/del/{id}")    
    public ResponseEntity<ResponseDto> eliminar(@PathVariable Long id){
        ResponseDto response = serviceAplicacion.eliminar(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
