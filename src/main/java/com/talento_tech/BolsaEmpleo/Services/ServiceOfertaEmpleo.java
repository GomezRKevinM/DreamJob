package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Entities.FiltroOferta;
import com.talento_tech.BolsaEmpleo.Entities.OfertaEmpleo;
import com.talento_tech.BolsaEmpleo.Repositories.OfertaEmpleoRepository;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOfertaEmpleo {

    private final OfertaEmpleoRepository repository;

    @Autowired
    public ServiceOfertaEmpleo(OfertaEmpleoRepository repository) {
        this.repository = repository;
    }

    public ResponseDto agregar(OfertaEmpleo oferta) {
        int resultado = repository.save(oferta);
        if (resultado > 0) {
            return new ResponseDto("Oferta de trabajo agregada exitosamente", oferta, 201);
        } else {
            return new ResponseDto("Error al agregar la oferta de trabajo", null, 400);
        }
    }

    public ResponseDto actualizar(OfertaEmpleo oferta) {
        int resultado = repository.update(oferta);
        if (resultado > 0) {
            return new ResponseDto("Oferta de trabajo actualizada exitosamente", oferta, 200);
        } else {
            return new ResponseDto("Error al actualizar la oferta de trabajo", null, 400);
        }
    }

    public ResponseDto eliminar(Long id) {
        int resultado = repository.deleteById(id);
        if (resultado > 0) {
            return new ResponseDto("Oferta de trabajo eliminada exitosamente", id, 200);
        } else {
            return new ResponseDto("No se pudo eliminar la oferta", null, 404);
        }
    }

    public ResponseDto listar() {
        List<OfertaEmpleo> ofertas = repository.findAll();
        return new ResponseDto("Lista de ofertas obtenida", ofertas, 200);
    }

    public ResponseDto contar() {
        int total = repository.count();
        return new ResponseDto("Cantidad total de ofertas", total, 200);
    }

    public ResponseDto obtenerPorId(Long id) {
        return repository.findById(id)
                .map(oferta -> new ResponseDto("Oferta encontrada", oferta, 200))
                .orElseGet(() -> new ResponseDto("No se encontró la oferta con id " + id, null, 404));
    }

    public ResponseDto contarByEmpresa(Long id){
        int total = repository.countByEmpresa(id);
        return new ResponseDto("Cantidad total de ofertas para la empresa #"+id, total, 200);
    }

    public ResponseDto filtrar(FiltroOferta filtro){
        List<OfertaEmpleo> ofertas = repository.filtrar(filtro);
        return new ResponseDto("Lista de ofertas encontrada", ofertas, 200);
    }
}
