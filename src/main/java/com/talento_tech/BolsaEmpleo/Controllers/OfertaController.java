package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.FiltroOferta;
import com.talento_tech.BolsaEmpleo.Entities.OfertaEmpleo;
import com.talento_tech.BolsaEmpleo.Services.ServiceAplicacion;
import com.talento_tech.BolsaEmpleo.Services.ServiceOfertaEmpleo;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ofertas")
public class OfertaController {

    private final ServiceOfertaEmpleo serviceOfertaEmpleo;
    private final ServiceAplicacion serviceAplicacion;

    @Autowired
    public OfertaController(ServiceOfertaEmpleo serviceOfertaEmpleo,
                                  ServiceAplicacion serviceAplicacion) {
        this.serviceOfertaEmpleo = serviceOfertaEmpleo;
        this.serviceAplicacion = serviceAplicacion;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> crear(@RequestBody OfertaEmpleo oferta) {
        ResponseDto response = serviceOfertaEmpleo.agregar(oferta);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> obtener(@PathVariable Long id) {
        ResponseDto response = serviceOfertaEmpleo.obtenerPorId(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> editar(@RequestBody OfertaEmpleo oferta) {
        ResponseDto response = serviceOfertaEmpleo.actualizar(oferta);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> eliminar(@PathVariable Long id) {
        ResponseDto response = serviceOfertaEmpleo.eliminar(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> listar() {
        ResponseDto response = serviceOfertaEmpleo.listar();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}/aplicaciones")
    public ResponseEntity<ResponseDto> getAplicaciones(@PathVariable Long id) {
        ResponseDto response = serviceAplicacion.getByOferta(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/filtrar")
    public ResponseEntity<ResponseDto> filtrar(@RequestBody FiltroOferta filtro) {
        ResponseDto response = serviceOfertaEmpleo.filtrar(filtro);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
