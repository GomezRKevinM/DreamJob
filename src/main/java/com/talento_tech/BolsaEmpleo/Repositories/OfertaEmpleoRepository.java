package com.talento_tech.BolsaEmpleo.Repositories;

import com.talento_tech.BolsaEmpleo.Entities.FiltroOferta;
import com.talento_tech.BolsaEmpleo.Entities.OfertaEmpleo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Repository
public class OfertaEmpleoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OfertaEmpleoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<OfertaEmpleo> ofertaRowMapper = (rs, rowNum) -> {
        OfertaEmpleo oferta = new OfertaEmpleo();
        oferta.setId(rs.getLong("id"));
        oferta.setEmpresa_id(rs.getLong("id_empresa"));
        oferta.setNombreEmpresa(rs.getString("nombre"));
        oferta.setEmpleador_id(rs.getLong("id_empleador"));
        oferta.setCargo(rs.getString("cargo"));
        oferta.setUbicacion(rs.getString("ubicacion"));
        oferta.setDescripcion(rs.getString("descripcion"));
        oferta.setRequisitos(rs.getString("requisitos"));
        oferta.setSalario(rs.getDouble("salario"));
        oferta.setFechaPublicacion(rs.getTimestamp("fecha_publicacion"));
        oferta.setFechaExpiracion(rs.getDate("fechaexpiracion"));
        oferta.setTipoContrato(rs.getString("tipo_contrato"));
        oferta.setModalidad(rs.getString("modalidad"));
        oferta.setPais(rs.getString("pais"));
        oferta.setCiudad(rs.getString("ciudad"));
        oferta.setDepartamento(rs.getString("departamento"));
        oferta.setNivelEstudios(rs.getString("nivel_estudios"));
        oferta.setExperienciaLaboral(rs.getString("experiencia_laboral"));
        oferta.setIdiomas(rs.getString("idiomas"));
        return oferta;
    };


    public List<OfertaEmpleo> findAll() {
        String sql = "SELECT * FROM ofertatrabajo INNER JOIN empresas ON id_empresa = empresa_id";
        return jdbcTemplate.query(sql, ofertaRowMapper);
    }
    
    public List<OfertaEmpleo> findByEmpresaId(Long id) {
        String sql = "SELECT * FROM ofertatrabajo INNER JOIN WHERE id_empresa = ?";
        return jdbcTemplate.query(sql, ofertaRowMapper, id);
    }

    public Optional<OfertaEmpleo> findById(Long id) {
        String sql = "SELECT * FROM ofertatrabajo INNER JOIN empresas ON id_empresa = empresa_id WHERE id = ?";
        try {
            OfertaEmpleo oferta = jdbcTemplate.queryForObject(sql, new Object[]{id}, ofertaRowMapper);
            return Optional.ofNullable(oferta);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int save(OfertaEmpleo oferta) {
        String sql = "INSERT INTO ofertatrabajo (id_empleador, ide_empresa, cargo, ubicacion, descripcion, requisitos, salario, fechaexpiracion, tipo_contrato, modalidad, pais, ciudad, departamento, nivel_estudios, experiencia_laboral, idiomas) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            oferta.getEmpleador_id(),
            oferta.getEmpresa_id(),
            oferta.getCargo(),
            oferta.getUbicacion(),
            oferta.getDescripcion(),
            oferta.getRequisitos(),
            oferta.getSalario(),
            oferta.getFechaPublicacion(),
            oferta.getTipoContrato(),
            oferta.getModalidad(),
            oferta.getPais(),
            oferta.getCiudad(),
            oferta.getDepartamento(),
            oferta.getNivelEstudios(),
            oferta.getExperienciaLaboral(),
            oferta.getIdiomas()
        );
    }

    public int update(OfertaEmpleo oferta) {
        String sql = "UPDATE ofertas_empleo SET id_empleador = ?, cargo = ?, ubicacion = ?, descripcion = ?, requisitos = ?, salario = ?, fechaexpiracion = ?, tipo_contrato = ?, modalidad = ?, pais = ?, ciudad = ?, departamento = ?, nivel_estudios = ?, experiencia_laboral = ?, idiomas = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
            oferta.getEmpleador_id(),
            oferta.getCargo(),
            oferta.getUbicacion(),
            oferta.getDescripcion(),
            oferta.getRequisitos(),
            oferta.getSalario(),
            oferta.getFechaExpiracion(),
            oferta.getTipoContrato(),
            oferta.getModalidad(),
            oferta.getPais(),
            oferta.getCiudad(),
            oferta.getDepartamento(),
            oferta.getNivelEstudios(),
            oferta.getExperienciaLaboral(),
            oferta.getIdiomas(),
            oferta.getId()
        );
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM ofertatrabajo WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM ofertatrabajo";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int countByEmpresa(Long empresaID){
        String sql = "SELECT COUNT(*) FROM ofertatrabajo WHERE id_empresa = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class,empresaID);
    }

    public List<OfertaEmpleo> filtrar(FiltroOferta filtro){
        String sql = "SELECT * FROM ofertatrabajo INNER JOIN empresas ON id_empresa = empresa_id WHERE cargo LIKE '%"+filtro.getBusqueda()+"%' AND ciudad LIKE '%"+filtro.getCiudad()+"%' AND pais LIKE '%"+filtro.getPais()+"%' AND departamento LIKE '%"+filtro.getDepartamento()+"%' nivel_estudios LIKE '%"+filtro.getNivelEstudios()+"%' AND experiencia_laboral LIKE '%"+filtro.getExperienciaLaboral()+"%' AND idiomas LIKE '%"+filtro.getIdiomas()+"%' AND modalidad LIKE '%"+filtro.getModalidad()+"%' AND tipo_contrato LIKE '%"+filtro.getTipoContrato()+"%' ";
        return jdbcTemplate.query(sql,ofertaRowMapper);
    }
}

