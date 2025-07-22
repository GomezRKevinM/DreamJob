package com.talento_tech.BolsaEmpleo.Services;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://aws-0-us-east-2.pooler.supabase.com:6543/postgres");
        config.setUsername("postgres.tfgnncxxcoycjuwiceby");
        config.setPassword("Talento@Tech#2025");

        // Configuraciones de pool de conexiones
        config.setMaximumPoolSize(10);  // Máximo de conexiones simultáneas
        config.setMinimumIdle(5);       // Conexiones mínimas en reposo
        config.setIdleTimeout(30000);   // Tiempo de espera para conexiones inactivas
        config.setMaxLifetime(2000000); // Tiempo máximo de vida de conexión
        config.setConnectionTimeout(30000); // Tiempo máximo de espera para obtener conexión

        // Configuraciones adicionales de Supabase
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");

        return new HikariDataSource(config);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
