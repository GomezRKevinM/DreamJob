package com.talento_tech.BolsaEmpleo.Controllers;

import com.talento_tech.BolsaEmpleo.Entities.Empleado;
import com.talento_tech.BolsaEmpleo.Services.ServiceEmpleado;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final ServiceEmpleado ServiceEmpleado;

    @Autowired
    public EmpleadoController(ServiceEmpleado ServiceEmpleado) {
        this.ServiceEmpleado = ServiceEmpleado;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto> listar(){
        ResponseDto response = ServiceEmpleado.list();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getEmpleadoById(@PathVariable Long id) {
        ResponseDto response = ServiceEmpleado.getEmpleadoById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDto> getEmpleadoByUser(@PathVariable Long id) {
        ResponseDto response = ServiceEmpleado.getEmpleadoByUser(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<ResponseDto> getEmpleadosByEmpresaId(@PathVariable Long empresaId) {
        ResponseDto response = ServiceEmpleado.getEmpleadosByEmpresaId(empresaId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> agregar(@RequestBody Empleado empleado) {
        ResponseDto response = ServiceEmpleado.agregar(empleado);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> editar(@RequestBody Empleado empleado) {
        ResponseDto response = ServiceEmpleado.actualizar(empleado);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{empleadoId}")
    public ResponseEntity<ResponseDto> eliminar(@PathVariable Long empleadoId) {
        ResponseDto response = ServiceEmpleado.eliminar(empleadoId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
