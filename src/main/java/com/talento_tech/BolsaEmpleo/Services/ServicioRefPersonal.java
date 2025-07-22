package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Entities.ReferenciaPersonal;
import com.talento_tech.BolsaEmpleo.Repositories.ReferenciaPersonalRepository;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioRefPersonal {

    private final ReferenciaPersonalRepository referenciaRepo;

    @Autowired
    public ServicioRefPersonal(ReferenciaPersonalRepository referenciaRepo) {
        this.referenciaRepo = referenciaRepo;
    }

    public ResponseDto agregar(ReferenciaPersonal referencia) {
        try {
            int filas = referenciaRepo.save(referencia);
            if (filas > 0) {
                return new ResponseDto("Referencia personal agregada exitosamente", filas, 201);
            } else {
                return new ResponseDto("No se pudo agregar la referencia personal", null, 400);
            }
        } catch (Exception e) {
            return new ResponseDto("Error al agregar referencia personal", e.getMessage(), 500);
        }
    }

    public ResponseDto actualizar(ReferenciaPersonal referencia) {
        try {
            int filas = referenciaRepo.update(referencia);
            if (filas > 0) {
                return new ResponseDto("Referencia personal actualizada exitosamente", referencia, 200);
            } else {
                return new ResponseDto("No se encontró la referencia a actualizar", null, 404);
            }
        } catch (Exception e) {
            return new ResponseDto("Error al actualizar referencia", e.getMessage(), 500);
        }
    }

    public ResponseDto eliminar(Long id) {
        try {
            int filas = referenciaRepo.deleteById(id);
            if (filas > 0) {
                return new ResponseDto("Referencia personal eliminada exitosamente", filas, 200);
            } else {
                return new ResponseDto("No se encontró la referencia a eliminar", null, 404);
            }
        } catch (Exception e) {
            return new ResponseDto("Error al eliminar referencia", e.getMessage(), 500);
        }
    }

    public ResponseDto getReferencia(Long id) {
        Optional<ReferenciaPersonal> referencia = referenciaRepo.findById(id);
        return new ResponseDto("Referencia #"+id, referencia, 200);
    }

    public ResponseDto getReferenciasByEmpleado(Long empleadoId) {
        List<ReferenciaPersonal> referencias = referenciaRepo.findByEmpleadoId(empleadoId);
        return new ResponseDto("Referencias del empleado "+empleadoId, referencias, 200);
    }
}
