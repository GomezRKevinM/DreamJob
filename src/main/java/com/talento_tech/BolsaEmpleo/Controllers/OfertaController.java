package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.OfertaEmpleo;
import com.talento_tech.BolsaEmpleo.Services.ServiceOfertaEmpleo;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oferta-empleo")
public class OfertaController {
    private ServiceOfertaEmpleo serviceOfertaEmpleo;

    public OfertaController() {
        serviceOfertaEmpleo = new ServiceOfertaEmpleo();
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> crear(@RequestBody OfertaEmpleo oferta){
        ResponseDto response = serviceOfertaEmpleo.agregar(oferta);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> obtener(@PathVariable Long id){
        ResponseDto response = serviceOfertaEmpleo.getOferta(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editar(@RequestBody OfertaEmpleo oferta){
        ResponseDto response = serviceOfertaEmpleo.actualizar(oferta);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<ResponseDto> eliminar(@PathVariable Long id){
        ResponseDto response = serviceOfertaEmpleo.eliminar(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> listar(){
        ResponseDto response = serviceOfertaEmpleo.lista();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
