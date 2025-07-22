package com.talento_tech.BolsaEmpleo.Repositories;

import com.talento_tech.BolsaEmpleo.Entities.Aplicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class AplicacionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AplicacionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Aplicacion> aplicacionRowMapper = (rs, rowNum) -> {
        Aplicacion aplicacion = new Aplicacion();
        aplicacion.setId(rs.getLong("aplicacion_id"));
        aplicacion.setIdEmpleado(rs.getLong("empleado_id"));
        aplicacion.setIdOfertaEmpleo(rs.getLong("oferta_id"));
        aplicacion.setComentario(rs.getString("comentario"));
        aplicacion.setEstado(rs.getString("estado"));
        return aplicacion;
    };

    public List<Aplicacion> findAll() {
        String sql = "SELECT * FROM aplicaciones";
        return jdbcTemplate.query(sql, aplicacionRowMapper);
    }

    public Optional<Aplicacion> findById(Long id) {
        String sql = "SELECT * FROM aplicaciones WHERE id = ?";
        try {
            Aplicacion aplicacion = jdbcTemplate.queryForObject(sql, new Object[]{id}, aplicacionRowMapper);
            return Optional.ofNullable(aplicacion);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int save(Aplicacion aplicacion) {
        String sql = "INSERT INTO aplicaciones (empleado_id, oferta_id, estado,comentario) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            aplicacion.getIdEmpleado(),
            aplicacion.getIdOfertaEmpleo(),
            aplicacion.getEstado(), aplicacion.getComentario()
        );
    }

    public int update(Aplicacion aplicacion) {
        String sql = "UPDATE aplicaciones SET empleado_id = ?, oferta_id = ?, comentario = ?, estado = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
            aplicacion.getIdEmpleado(),
            aplicacion.getIdOfertaEmpleo(),
            aplicacion.getComentario(),
            aplicacion.getEstado(),
            aplicacion.getId()
        );
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM aplicaciones WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM aplicaciones";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}

