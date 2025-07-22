package com.talento_tech.BolsaEmpleo.Repositories;

import com.talento_tech.BolsaEmpleo.Entities.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EmpresaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmpresaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Empresa> empresaRowMapper = (rs, rowNum) -> {
        Empresa empresa = new Empresa(
            rs.getString("nombre"),
            rs.getString("descripcion"),
            rs.getString("logo"),
            rs.getString("telefono"),
            rs.getString("direccion"),
            rs.getString("email"),
            rs.getString("web"),
            rs.getString("tipo_empresa"),
            rs.getString("nit"),
            rs.getString("representante"),
            rs.getString("representante_identificacion"),
            rs.getString("representante_tipo_identificacion")
        );
        empresa.setId(rs.getLong("empresa_id"));
        empresa.setUsuaro_id(rs.getLong("usuario_id"));
        return empresa;
    };

    public List<Empresa> findAll() {
        String sql = "SELECT * FROM empresas";
        return jdbcTemplate.query(sql, empresaRowMapper);
    }

    public Optional<Empresa> findById(Long id) {
        String sql = "SELECT * FROM empresas WHERE empresa_id = ?";
        try {
            Empresa empresa = jdbcTemplate.queryForObject(sql, new Object[]{id}, empresaRowMapper);
            return Optional.ofNullable(empresa);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Empresa> findByUserId(Long userId) {
        String sql = "SELECT * FROM empresas WHERE usuario_id = ?";
        try {
            Empresa empresa = jdbcTemplate.queryForObject(sql, new Object[]{userId}, empresaRowMapper);
            return Optional.ofNullable(empresa);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int save(Empresa empresa) {
        String sql = "INSERT INTO empresas (nombre,descripcion,logo,telefono,direccion,email,web,tipo_empresa,nit,representante,representante_identificacion,representante_tipo_identificacion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
            empresa.getNombre(),
            empresa.getDescripcion(),
            empresa.getLogo(),
            empresa.getTelefono(),
            empresa.getDireccion(),
            empresa.getEmail(),
            empresa.getWeb(),
            empresa.getTipoEmpresa(),
            empresa.getNIT(),
            empresa.getRepresentante(),
            empresa.getIdentificacionRepresentante(),
            empresa.getTipoIdentificacionRepresentante()
        );
    }

    public int update(Empresa empresa) {
        String sql = "UPDATE empresas SET nombre = ?, descripcion = ?, logo = ?, telefono = ?, direccion = ?, email = ?, web = ?, tipo_empresa = ?, nit = ?, representante = ?, representante_identificacion = ?, representante_tipo_identificacion = ? WHERE empresa_id = ?";
        return jdbcTemplate.update(sql,
            empresa.getNombre(),
            empresa.getDescripcion(),
            empresa.getLogo(),
            empresa.getTelefono(),
            empresa.getDireccion(),
            empresa.getEmail(),
            empresa.getWeb(),
            empresa.getTipoEmpresa(),
            empresa.getNIT(),
            empresa.getRepresentante(),
            empresa.getIdentificacionRepresentante(),
            empresa.getTipoIdentificacionRepresentante(),
            empresa.getId()
        );
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM empresas WHERE empresa_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM empresas";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}

