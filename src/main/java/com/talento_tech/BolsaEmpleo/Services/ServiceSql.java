package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Entities.SQLQuery;
import com.talento_tech.BolsaEmpleo.Repositories.SqlQueryRepository;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ServiceSql {

    private final SqlQueryRepository sqlRepository;

    @Autowired
    public ServiceSql(SqlQueryRepository sqlRepository) {
        this.sqlRepository = sqlRepository;
    }

    public ResponseDto consultarSQL(SQLQuery sentencia) {
        try {
            String sql = sentencia.getQuery().trim().toLowerCase();
            if (sql.startsWith("select")) {
                List<Map<String, Object>> resultados = sqlRepository.ejecutarConsulta(sentencia.getQuery());
                return new ResponseDto("Consulta ejecutada exitosamente", resultados, 200);
            } else {
                int filasAfectadas = sqlRepository.ejecutarActualizacion(sentencia.getQuery());
                return new ResponseDto("Operación ejecutada exitosamente",
                        Map.of("filasAfectadas", filasAfectadas), 200);
            }
        } catch (Exception e) {
            return new ResponseDto("Error al ejecutar la consulta SQL",
                    e.getMessage(), 500);
        }
    }
}
