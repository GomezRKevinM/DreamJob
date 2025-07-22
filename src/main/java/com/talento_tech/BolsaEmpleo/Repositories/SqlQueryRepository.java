package com.talento_tech.BolsaEmpleo.Repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SqlQueryRepository {

    private final JdbcTemplate jdbcTemplate;

    public SqlQueryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> ejecutarConsulta(String sql) {
        return jdbcTemplate.query(sql, new ColumnMapRowMapper());
    }

    public int ejecutarActualizacion(String sql) {
        return jdbcTemplate.update(sql);
    }
}

