package com.talento_tech.BolsaEmpleo.Repositories;

import com.talento_tech.BolsaEmpleo.Entities.ReferenciaPersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReferenciaPersonalRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReferenciaPersonalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<ReferenciaPersonal> referenciaRowMapper = (rs, rowNum) -> {
        ReferenciaPersonal referencia = new ReferenciaPersonal(rs.getLong("id"),rs.getString("nombre"), rs.getString("telefono"),rs.getString("parentezco"),rs.getLong("empleado_id"));
        return referencia;
    };

    public List<ReferenciaPersonal> findAll() {
        String sql = "SELECT * FROM refpersonal";
        return jdbcTemplate.query(sql, referenciaRowMapper);
    }

    public Optional<ReferenciaPersonal> findById(Long id) {
        String sql = "SELECT * FROM refpersonal WHERE id = ?";
        try {
            ReferenciaPersonal referencia = jdbcTemplate.queryForObject(sql, new Object[]{id}, referenciaRowMapper);
            return Optional.ofNullable(referencia);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<ReferenciaPersonal> findByEmpleadoId(Long id) {
        String sql = "SELECT * FROM refpersonal WHERE empleado_id = ?";
        return jdbcTemplate.query(sql, referenciaRowMapper, id);
    }

    public int save(ReferenciaPersonal referencia) {
        String sql = "INSERT INTO refpersonal (empleado_id, nombre, telefono, parentezco) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            referencia.getEmpleado_id(),
            referencia.getNombre(),
            referencia.getTelefono(),
            referencia.getParentezco()
        );
    }

    public int update(ReferenciaPersonal referencia) {
        String sql = "UPDATE refpersonal SET nombre = ?, telefono = ?, parentezco = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
            referencia.getNombre(),
            referencia.getTelefono(),
            referencia.getParentezco(),
            referencia.getId()
        );
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM refpersonal WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int count(Long id) {
        String sql = "SELECT COUNT(*) FROM refpersonal WHERE empleado_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class,id);
    }
}

