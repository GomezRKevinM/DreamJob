package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Controllers.DatabaseConexion;
import com.talento_tech.BolsaEmpleo.Controllers.SQLController;
import com.talento_tech.BolsaEmpleo.Entities.SQLQuery;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServiceSql {

    public ResponseDto consultarSQL(SQLQuery sentencia){
        try (Connection conn = DatabaseConexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sentencia.getQuery())) {

            if (sentencia.getQuery().toLowerCase().trim().startsWith("select")) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Object> resultados = new ArrayList<>();
                while (rs.next()) {
                    Map<String, Object> fila = new HashMap<>();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        fila.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                    }
                    resultados.add(fila);
                }
                return new ResponseDto("Consulta ejecutada exitosamente", resultados, 200);
            } else {
                int filasAfectadas = ps.executeUpdate();
                return new ResponseDto("Operación ejecutada exitosamente",
                        Map.of("filasAfectadas", filasAfectadas), 200);
            }
        } catch (SQLException e) {
            return new ResponseDto("Error al ejecutar la consulta SQL",
                    e.getMessage(), 500);
        }
    }
}
