package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Entities.Empresa;
import com.talento_tech.BolsaEmpleo.Repositories.EmpresaRepository;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmpresa {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public ServiceEmpresa(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public ResponseDto getEmpresaById(Long id) {
        Optional<Empresa> empresaOpt = empresaRepository.findById(id);
        if (empresaOpt.isPresent()) {
            return new ResponseDto("Empresa encontrada", empresaOpt.get(), 200);
        } else {
            return new ResponseDto("Empresa no encontrada", null, 404);
        }
    }

    public ResponseDto getEmpresaByUser(Long userId) {
        Optional<Empresa> empresaOpt = empresaRepository.findByUserId(userId);
        if (empresaOpt.isPresent()) {
            return new ResponseDto("Empresa encontrada", empresaOpt.get(), 200);
        } else {
            return new ResponseDto("Empresa no encontrada", null, 404);
        }
    }

    public ResponseDto crearEmpresa(Empresa empresa) {
        int rowsAffected = empresaRepository.save(empresa);
        if (rowsAffected > 0) {
            return new ResponseDto("Empresa creada exitosamente", empresa, 201);
        } else {
            return new ResponseDto("Error al crear la empresa", null, 400);
        }
    }

    public ResponseDto editarEmpresa(Empresa empresa) {
        int rowsAffected = empresaRepository.update(empresa);
        if (rowsAffected > 0) {
            return new ResponseDto("Empresa editada exitosamente", empresa, 200);
        } else {
            return new ResponseDto("Empresa no encontrada o sin cambios", null, 404);
        }
    }

    public ResponseDto eliminarEmpresa(Long empresaId) {
        Optional<Empresa> empresaOpt = empresaRepository.findById(empresaId);
        if (empresaOpt.isEmpty()) {
            return new ResponseDto("Empresa no encontrada", null, 404);
        }

        int rowsAffected = empresaRepository.deleteById(empresaId);
        if (rowsAffected > 0) {
            return new ResponseDto("Empresa eliminada exitosamente", empresaOpt.get(), 200);
        } else {
            return new ResponseDto("Error al eliminar la empresa", null, 400);
        }
    }

    public ResponseDto getEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();
        return new ResponseDto("Empresas obtenidas exitosamente", empresas, 200);
    }

    public ResponseDto contar() {
        try {
            int total = empresaRepository.count();
            return new ResponseDto("Total de empresas encontradas", total, 200);
        } catch (Exception e) {
            return new ResponseDto("Error al contar las empresas", e.getMessage(), 500);
        }
    }
}
