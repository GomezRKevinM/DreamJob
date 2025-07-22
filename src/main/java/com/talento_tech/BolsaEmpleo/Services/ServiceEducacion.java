package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Entities.Educacion;
import com.talento_tech.BolsaEmpleo.Repositories.EducacionRepository;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceEducacion {

    private final EducacionRepository educacionRepository;

    @Autowired
    public ServiceEducacion(EducacionRepository educacionRepository) {
        this.educacionRepository = educacionRepository;
    }

    public ResponseDto getEducacion(Long id) {
        try {
            Optional<Educacion> educacion = educacionRepository.findById(id);
            if (educacion.isPresent()) {
                return new ResponseDto("Educación encontrada", educacion.get(), 200);
            } else {
                return new ResponseDto("No se encontró educación con el ID #" + id, null, 404);
            }
        } catch (Exception e) {
            return new ResponseDto("Error al obtener educación #" + id, e.getMessage(), 500);
        }
    }

    public ResponseDto getEducacionByEmpleadoId(Long empleadoId) {
        try {
            List<Educacion> todas = educacionRepository.findAll();
            List<Educacion> filtradas = todas.stream()
                    .filter(e -> e.getEmpleado_id() == empleadoId)
                    .collect(Collectors.toList());

            return new ResponseDto("Lista de educación obtenida", filtradas, 200);
        } catch (Exception e) {
            return new ResponseDto("Error al obtener educación del usuario", e.getMessage(), 500);
        }
    }

    public ResponseDto agregar(Educacion educacion) {
        try {
            int filas = educacionRepository.save(educacion);
            if (filas > 0) {
                return new ResponseDto("Educación agregada exitosamente", educacion, 200);
            } else {
                return new ResponseDto("No se pudo agregar la educación", null, 400);
            }
        } catch (Exception e) {
            return new ResponseDto("Error al agregar educación", e.getMessage(), 500);
        }
    }

    public ResponseDto actualizar(Educacion educacion) {
        try {
            int filas = educacionRepository.update(educacion);
            if (filas > 0) {
                return new ResponseDto("Datos de educación actualizados exitosamente", educacion, 200);
            } else {
                return new ResponseDto("No se pudo actualizar los datos de la educación", null, 400);
            }
        } catch (Exception e) {
            return new ResponseDto("Error al actualizar educación", e.getMessage(), 500);
        }
    }

    public ResponseDto eliminar(Long id) {
        try {
            int filas = educacionRepository.deleteById(id);
            if (filas > 0) {
                return new ResponseDto("Educación eliminada exitosamente", id, 200);
            } else {
                return new ResponseDto("No se pudo eliminar la educación", null, 400);
            }
        } catch (Exception e) {
            return new ResponseDto("Error al eliminar datos", e.getMessage(), 500);
        }
    }
}
