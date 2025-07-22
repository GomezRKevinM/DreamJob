package com.talento_tech.BolsaEmpleo.Repositories;

import com.talento_tech.BolsaEmpleo.Entities.Educacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EducacionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EducacionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Educacion> educacionRowMapper = (rs, rowNum) -> {
        Educacion educacion = new Educacion();
        educacion.setId(rs.getLong("id"));
        educacion.setEmpleado_id(rs.getLong("empleado_id"));
        educacion.setTitulo(rs.getString("titulo"));
        educacion.setInstitucion(rs.getString("institucion"));
        educacion.setTipo_institucion(rs.getString("tipo_instituto"));
        educacion.setFecha_inicio(rs.getDate("inicio"));
        educacion.setFecha_fin(rs.getDate("termino"));
        educacion.setCursando_actualmente(rs.getBoolean("cursando_actualmente"));
        educacion.setPais(rs.getString("pais"));
        educacion.setDepartamento(rs.getString("departamento"));
        educacion.setCiudad(rs.getString("ciudad"));
        return educacion;
    };

    public List<Educacion> findAll() {
        String sql = "SELECT * FROM educacion";
        return jdbcTemplate.query(sql, educacionRowMapper);
    }

    public Optional<Educacion> findById(Long id) {
        String sql = "SELECT * FROM educacion WHERE id = ?";
        try {
            Educacion educacion = jdbcTemplate.queryForObject(sql, new Object[]{id}, educacionRowMapper);
            return Optional.ofNullable(educacion);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public int save(Educacion educacion) {
        String sql = "INSERT INTO educacion (empleado_id, tipo_instituto, institucion, titulo, inicio, termino, cursando_actualmente, pais, departamento, ciudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            educacion.getEmpleado_id(),
            educacion.getTipo_institucion(),
            educacion.getInstitucion(),
            educacion.getTitulo(),
            educacion.getFecha_inicio(),
            educacion.getFecha_fin(),
            educacion.getCursando_actualmente(),
            educacion.getPais(),
            educacion.getDepartamento(),
            educacion.getCiudad()
        );
    }

    public int update(Educacion educacion) {
        String sql = "UPDATE educacion SET tipo_instituto = ?, institucion = ?, titulo = ?, inicio = ?, termino = ?, cursando_actualmente = ?, pais = ?, departamento = ?, ciudad = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
            educacion.getTipo_institucion(),
            educacion.getInstitucion(),
            educacion.getTitulo(),
            educacion.getFecha_inicio(),
            educacion.getFecha_fin(),
            educacion.getCursando_actualmente(),
            educacion.getPais(),
            educacion.getDepartamento(),
            educacion.getCiudad(),
            educacion.getId()
        );
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM educacion WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM educacion";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}

