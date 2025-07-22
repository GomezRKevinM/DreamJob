package com.talento_tech.BolsaEmpleo.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talento_tech.BolsaEmpleo.Entities.Aplicacion;
import com.talento_tech.BolsaEmpleo.Entities.UserSesion;
import com.talento_tech.BolsaEmpleo.Services.ServiceAplicacion;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/aplicacion")
@CrossOrigin(origins = "*")
public class AplicacionController {

    private final ServiceAplicacion serviceAplicacion;
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @Autowired
    public AplicacionController(ServiceAplicacion serviceAplicacion) {
        this.serviceAplicacion = serviceAplicacion;
        this.restTemplate = new RestTemplate();
        this.mapper = new ObjectMapper();
    }

    @PostMapping("/aplicar")
    public ResponseEntity<ResponseDto> aplicar(@RequestBody Aplicacion aplicacion) {
        try {
            String url = "http://localhost:8080/users/user-session";
            ResponseDto usuario = restTemplate.getForObject(url, ResponseDto.class);

            if (usuario != null && usuario.getData() != null) {
                UserSesion userSesion = mapper.convertValue(usuario.getData(), UserSesion.class);
                if (!"empleado".equalsIgnoreCase(userSesion.getUser().getRol())) {
                    return ResponseEntity
                            .status(401)
                            .body(new ResponseDto("Rol no autorizado para aplicar a la oferta", userSesion.getUser(), 401));
                }
            }

            ResponseDto response = serviceAplicacion.aplicar(
                    aplicacion.getIdOfertaEmpleo(),
                    aplicacion.getIdEmpleado()
            );

            return ResponseEntity.status(response.getStatus()).body(response);

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(new ResponseDto("Error al aplicar", e.getMessage(), 500));
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<ResponseDto> listar(@RequestParam Long idOfertaEmpleo) {
        ResponseDto response = serviceAplicacion.getByOferta(idOfertaEmpleo);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> obtener(@PathVariable Long id) {
        ResponseDto response = serviceAplicacion.get(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDto> update(@RequestBody Aplicacion aplicacion) {
        ResponseDto response = serviceAplicacion.update(
                aplicacion.getId(),
                aplicacion.getEstado(),
                aplicacion.getComentario()
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<ResponseDto> eliminar(@PathVariable Long id) {
        ResponseDto response = serviceAplicacion.eliminar(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
