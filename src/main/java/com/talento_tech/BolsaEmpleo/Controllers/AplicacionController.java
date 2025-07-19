package com.talento_tech.BolsaEmpleo.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talento_tech.BolsaEmpleo.Entities.Aplicacion;
import com.talento_tech.BolsaEmpleo.Entities.UserSesion;
import com.talento_tech.BolsaEmpleo.Services.ServiceAplicacion;
import com.talento_tech.BolsaEmpleo.Services.ServiceUsuario;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;


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
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/users/user-session";
            ResponseDto usuario = restTemplate.getForObject(url, ResponseDto.class);
            if(usuario.getData() != null){
                ObjectMapper mapper = new ObjectMapper();
                UserSesion userSesion = mapper.convertValue(usuario.getData(), UserSesion.class);
                if(!userSesion.getUser().getRol().equals("empleado")){
                    ResponseDto denegado = new ResponseDto("Rol no autorizado para aplicar a la oferta",userSesion.getUser(),401);
                    return ResponseEntity.status(denegado.getStatus()).body(denegado);
                }
            }
            ResponseDto response = serviceAplicacion.aplicar(aplicacion.getIdOfertaEmpleo(),aplicacion.getIdEmpleado());
            return ResponseEntity.status(response.getStatus()).body(response);
        }catch (Exception e){
            ResponseDto error = new ResponseDto("Error al aplicar",e.getMessage(),500);
            return ResponseEntity.status(500).body(error);
        }
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
