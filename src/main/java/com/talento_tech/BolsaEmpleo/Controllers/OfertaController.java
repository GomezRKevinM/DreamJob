package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.FiltroOferta;
import com.talento_tech.BolsaEmpleo.Entities.OfertaEmpleo;
import com.talento_tech.BolsaEmpleo.Services.ServiceAplicacion;
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

    @CrossOrigin(origins = "*")
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> crear(@RequestBody OfertaEmpleo oferta){
        ResponseDto response = serviceOfertaEmpleo.agregar(oferta);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> obtener(@PathVariable Long id){
        ResponseDto response = serviceOfertaEmpleo.getOferta(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/edit")
    public ResponseEntity<ResponseDto> editar(@RequestBody OfertaEmpleo oferta){
        ResponseDto response = serviceOfertaEmpleo.actualizar(oferta);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/del/{id}")
    public ResponseEntity<ResponseDto> eliminar(@PathVariable Long id){
        ResponseDto response = serviceOfertaEmpleo.eliminar(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/list")
    public ResponseEntity<ResponseDto> listar(){
        ResponseDto response = serviceOfertaEmpleo.lista();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/aplicaciones/{id}")
    public ResponseEntity<ResponseDto> getAplicaciones(@PathVariable Long id){
        ServiceAplicacion serviceAplicacion = new ServiceAplicacion();
        ResponseDto response = serviceAplicacion.getByOferta(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/buscar/{busqueda}")
    public ResponseEntity<ResponseDto> buscar(@PathVariable String busqueda) {
        ResponseDto response = serviceOfertaEmpleo.buscar(busqueda);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @CrossOrigin(origins = "*")
    @PatchMapping("/filtrar")
    public ResponseEntity<ResponseDto> filtrar(@RequestBody FiltroOferta filtro) {
        ResponseDto response = serviceOfertaEmpleo.filtrar(filtro.getBusqueda(),filtro.getPais(),filtro.getCiudad(),filtro.getDepartamento(),filtro.getNivelEstudios(),filtro.getExperienciaLaboral(),filtro.getIdiomas(),filtro.getModalidad(),filtro.getTipoContrato());
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
