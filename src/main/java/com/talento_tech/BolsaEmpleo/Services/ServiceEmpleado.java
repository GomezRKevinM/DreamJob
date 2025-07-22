package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Entities.Empleado;
import com.talento_tech.BolsaEmpleo.Repositories.EmpleadoRepository;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceEmpleado {

    private final EmpleadoRepository repository;

    @Autowired
    public ServiceEmpleado(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public ResponseDto getEmpleadosByEmpresaId(long empresaId) {
        // Este método necesita que tengas la columna empresa_id en la tabla y el método en el repositorio
        return new ResponseDto("Método no implementado en repositorio aún", null, 501);
    }

    public ResponseDto getEmpleadoById(long empleadoId) {
        return repository.findById(empleadoId)
                .map(emp -> new ResponseDto("Empleado encontrado", emp, 200))
                .orElseGet(() -> new ResponseDto("Empleado no encontrado", null, 404));
    }

    public ResponseDto getEmpleadoByUser(long userId) {
        return repository.findByUserId(userId)
                .map(emp -> new ResponseDto("Empleado encontrado", emp, 200))
                .orElseGet(() -> new ResponseDto("Empleado no encontrado", null, 404));
    }

    public ResponseDto agregar(Empleado empleado) {
        int result = repository.save(empleado);
        if (result > 0) {
            return new ResponseDto("Empleado agregado exitosamente", empleado, 201);
        } else {
            return new ResponseDto("Error al agregar el empleado", null, 400);
        }
    }

    public ResponseDto actualizar(Empleado empleado) {
        int result = repository.update(empleado);
        if (result > 0) {
            return new ResponseDto("Empleado actualizado", empleado, 200);
        } else {
            return new ResponseDto("Empleado no encontrado", null, 400);
        }
    }

    public ResponseDto eliminar(long empleadoId) {
        return getEmpleadoById(empleadoId).getData() == null
                ? new ResponseDto("Empleado no encontrado", null, 404)
                : (repository.deleteById(empleadoId) > 0
                ? new ResponseDto("Empleado eliminado exitosamente", empleadoId, 200)
                : new ResponseDto("Error al eliminar el empleado", null, 400));
    }
}
