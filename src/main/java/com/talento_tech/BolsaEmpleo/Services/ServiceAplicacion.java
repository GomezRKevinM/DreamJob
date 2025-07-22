package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Entities.Aplicacion;
import com.talento_tech.BolsaEmpleo.Repositories.AplicacionRepository;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAplicacion {

    private final AplicacionRepository aplicacionRepository;

    @Autowired
    public ServiceAplicacion(AplicacionRepository aplicacionRepository) {
        this.aplicacionRepository = aplicacionRepository;
    }

    public ResponseDto aplicar(Long oferta_id, Long empleado_id) {
        Aplicacion aplicacion = new Aplicacion();
        aplicacion.setIdEmpleado(empleado_id);
        aplicacion.setIdOfertaEmpleo(oferta_id);
        aplicacion.setEstado("Aplicado");
        aplicacion.setComentario(""); // opcional: por defecto vacío

        try {
            int rows = aplicacionRepository.save(aplicacion);
            if (rows > 0) {
                return new ResponseDto("Usuario aplicado a la oferta de empleo exitosamente", rows, 200);
            } else {
                return new ResponseDto("Error al aplicar oferta", null, 500);
            }
        } catch (Exception e) {
            return new ResponseDto("Error al aplicar oferta", e.getMessage(), 500);
        }
    }

    public ResponseDto update(Long id, String estado, String comentario) {
        Optional<Aplicacion> optional = aplicacionRepository.findById(id);
        if (optional.isPresent()) {
            Aplicacion aplicacion = optional.get();
            aplicacion.setEstado(estado);
            aplicacion.setComentario(comentario);

            try {
                int rows = aplicacionRepository.update(aplicacion);
                if (rows > 0) {
                    return new ResponseDto("Aplicación actualizada exitosamente", rows, 200);
                } else {
                    return new ResponseDto("No se pudo actualizar la aplicación", null, 500);
                }
            } catch (Exception e) {
                return new ResponseDto("Error al actualizar la aplicación", e.getMessage(), 500);
            }
        } else {
            return new ResponseDto("No se encontró la aplicación #" + id, null, 404);
        }
    }

    public ResponseDto eliminar(Long id) {
        try {
            int rows = aplicacionRepository.deleteById(id);
            if (rows > 0) {
                return new ResponseDto("Aplicación eliminada exitosamente", rows, 200);
            } else {
                return new ResponseDto("No se pudo eliminar la aplicación", null, 500);
            }
        } catch (Exception e) {
            return new ResponseDto("Error al eliminar la aplicación", e.getMessage(), 500);
        }
    }

    public ResponseDto getByOferta(Long ofertaId) {
        try {
            List<Aplicacion> todas = aplicacionRepository.findAll();
            List<Aplicacion> filtradas = todas.stream()
                    .filter(a -> a.getIdOfertaEmpleo().equals(ofertaId))
                    .toList();
            return new ResponseDto("Aplicaciones obtenidas exitosamente", filtradas, 200);
        } catch (Exception e) {
            return new ResponseDto("Error al obtener las aplicaciones", e.getMessage(), 500);
        }
    }

    public ResponseDto get(Long id) {
        Optional<Aplicacion> optional = aplicacionRepository.findById(id);
        if (optional.isPresent()) {
            return new ResponseDto("Aplicación obtenida exitosamente", optional.get(), 200);
        } else {
            return new ResponseDto("No se encontró la aplicación #" + id, null, 404);
        }
    }
}
