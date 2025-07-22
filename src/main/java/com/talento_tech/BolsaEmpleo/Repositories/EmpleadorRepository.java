package com.talento_tech.BolsaEmpleo.Repositories;

import com.talento_tech.BolsaEmpleo.Entities.Empleador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class EmpleadorRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmpleadorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Empleador> empleadorRowMapper = (rs, rowNum) -> {
        Empleador empleador = new Empleador();
        empleador.setId(rs.getLong("empleador_id"));
        empleador.setUsuario_id(rs.getLong("usuario_id"));
        empleador.setEmpresa_id(rs.getLong("empresa_id"));
        return empleador;
    };

    public List<Empleador> findAll() {
        String sql = "SELECT * FROM empleadores";
        return jdbcTemplate.query(sql, empleadorRowMapper);
    }

    public Optional<Empleador> findById(Long id) {
        String sql = "SELECT * FROM empleadores WHERE empleador_id = ?";
        try {
            Empleador empleador = jdbcTemplate.queryForObject(sql, new Object[]{id}, empleadorRowMapper);
            return Optional.ofNullable(empleador);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Empleador> findByUserId(Long userId) {
        String sql = "SELECT * FROM empleadores WHERE usuario_id = ?";
        try {
            Empleador empleador = jdbcTemplate.queryForObject(sql, new Object[]{userId}, empleadorRowMapper);
            return Optional.ofNullable(empleador);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int save(Empleador empleador) {
        String sql = "INSERT INTO empleadores (usuario_id, empresa_id) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
            empleador.getUsuario_id(),
            empleador.getEmpresa_id()
        );
    }

    public int update(Empleador empleador) {
        String sql = "UPDATE empleadores SET empresa_id = ? WHERE empleador_id = ?";
        return jdbcTemplate.update(sql,
            empleador.getEmpresa_id(),
            empleador.getId()
        );
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM empleadores WHERE empleador_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM empleadores";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}

