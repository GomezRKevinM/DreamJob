package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Entities.Empleador;
import com.talento_tech.BolsaEmpleo.Repositories.EmpleadorRepository;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmpleador {

    private final EmpleadorRepository empleadorRepository;

    @Autowired
    public ServiceEmpleador(EmpleadorRepository empleadorRepository) {
        this.empleadorRepository = empleadorRepository;
    }

    public ResponseDto getEmpleador(Long id) {
        Optional<Empleador> empleadorOpt = empleadorRepository.findById(id);
        if (empleadorOpt.isPresent()) {
            return new ResponseDto("Empleador encontrado", empleadorOpt.get(), 200);
        } else {
            return new ResponseDto("Empleador no encontrado", null, 404);
        }
    }

    public ResponseDto getEmpleadorByUser(Long userId) {
        Optional<Empleador> empleadorOpt = empleadorRepository.findByUserId(userId);
        if (empleadorOpt.isPresent()) {
            return new ResponseDto("Empleador encontrado", empleadorOpt.get(), 200);
        } else {
            return new ResponseDto("Empleador no encontrado", null, 404);
        }
    }

    public ResponseDto getEmpleadores() {
        List<Empleador> empleadores = empleadorRepository.findAll();
        return new ResponseDto("Empleadores encontrados", empleadores, 200);
    }

    public ResponseDto createEmpleador(Empleador empleador) {
        int filas = empleadorRepository.save(empleador);
        if (filas > 0) {
            return new ResponseDto("Empleador creado exitosamente", empleador, 201);
        } else {
            return new ResponseDto("Error al crear el empleador", null, 400);
        }
    }

    public ResponseDto updateEmpleador(Empleador empleador) {
        int filas = empleadorRepository.update(empleador);
        if (filas > 0) {
            return new ResponseDto("Empleador actualizado exitosamente", empleador, 200);
        } else {
            return new ResponseDto("Error al actualizar el empleador", null, 400);
        }
    }

    public ResponseDto deleteEmpleador(Long id) {
        int filas = empleadorRepository.deleteById(id);
        if (filas > 0) {
            return new ResponseDto("Empleador eliminado exitosamente", null, 200);
        } else {
            return new ResponseDto("Empleador no encontrado", null, 404);
        }
    }
}
