package com.talento_tech.BolsaEmpleo.Repositories;

import com.talento_tech.BolsaEmpleo.Entities.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class EmpleadoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmpleadoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Empleado> empleadoRowMapper = (rs, rowNum) -> {
        Empleado empleado = new Empleado();
        empleado.setEmpleado_id(rs.getLong("empleado_id"));
        empleado.setUsuario_id(rs.getLong("user_id"));
        empleado.setCv(rs.getString("cv"));
        return empleado;
    };

    public List<Empleado> findAll() {
        String sql = "SELECT * FROM empleados";
        return jdbcTemplate.query(sql, empleadoRowMapper);
    }

    public Optional<Empleado> findById(Long id) {
        String sql = "SELECT * FROM empleados WHERE empleado_id = ?";
        try {
            Empleado empleado = jdbcTemplate.queryForObject(sql, new Object[]{id}, empleadoRowMapper);
            return Optional.ofNullable(empleado);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Empleado> findByUserId(Long userId) {
        String sql = "SELECT * FROM empleados WHERE user_id = ?";
        try {
            Empleado empleado = jdbcTemplate.queryForObject(sql, new Object[]{userId}, empleadoRowMapper);
            return Optional.ofNullable(empleado);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int save(Empleado empleado) {
        String sql = "INSERT INTO empleados (cv, habilidades, idiomas, user_id) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                empleado.getCv(),
                empleado.getHabilidadesString(),
                empleado.getIdiomasString(),
                empleado.getUsuario_id()
        );
    }

    public int update(Empleado empleado) {
        String sql = "UPDATE empleados SET cv = ?, habilidades = ?, idiomas = ? WHERE empleado_id = ?";
        return jdbcTemplate.update(sql,
                empleado.getCv(),
                empleado.getHabilidadesString(),
                empleado.getIdiomasString(),
                empleado.getEmpleado_id()
        );
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM empleados WHERE empleado_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM empleados";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}

