package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.Empleado;
import com.talento_tech.BolsaEmpleo.Services.ServiceEmpleado;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
    ServiceEmpleado serviceEmpleado;
    public EmpleadoController() {
        serviceEmpleado = new ServiceEmpleado();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getEmpleadoById(@PathVariable Long id) {
        ResponseDto response = serviceEmpleado.getEmpleadoById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<ResponseDto> getEmpleadosByEmpresaId(@PathVariable Long empresaId) {
        ResponseDto response = serviceEmpleado.getEmpleadosByEmpresaId(empresaId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> agregar(@RequestBody Empleado entity) {
        ResponseDto response = serviceEmpleado.agregar(entity);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> editar(@RequestBody Empleado entity) {
        ResponseDto response = serviceEmpleado.actualizar(entity);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/remove/{empleadoId}")
    public ResponseEntity<ResponseDto> eliminar(@PathVariable Long empleadoId) {
        ResponseDto response = serviceEmpleado.eliminar(empleadoId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
