package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Controllers.DatabaseConexion;
import com.talento_tech.BolsaEmpleo.Entities.Empleador;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceEmpleador {
    public ResponseDto getEmpleador(Long id){
        String sql = "SELECT * FROM empleadores WHERE empleador_id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            return new ResponseDto("Empleador encontrado", rs, 200);
        }catch (SQLException err){
            return new ResponseDto("Error al obtener el empleador", err.getMessage(), 500);
        }
    }

    public ResponseDto getEmpleadores(){
        String sql = "SELECT * FROM empleadores";
        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            ResultSet rs = pstmt.executeQuery();
            return new ResponseDto("Empleadores encontrados", rs, 200);
        }catch (SQLException err){
            return new ResponseDto("Error al obtener los empleadores", err.getMessage(), 500);
        }
    }

    public ResponseDto createEmpleador(Empleador empleador){
        String sql = "INSERT INTO empleadores (empresa_id,usuario_id) VALUES(?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, empleador.getEmpresa_id());
            pstmt.setLong(2, empleador.getUsuario_id());
            int rowAffects = pstmt.executeUpdate();
            if(rowAffects > 0){
                return new ResponseDto("Empleador creado exitosamente", empleador, 201);
            }else {
                return new ResponseDto("Error al crear el empleador", pstmt.getMetaData(), 400);
            }
        }catch (SQLException err){
            return new ResponseDto("Error al crear el empleador", err.getMessage(), 500);
        }
    }

    public ResponseDto deleteEmpleador(Long id){
        String sql = "DELETE FROM empleadores WHERE empleador_id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            return new ResponseDto("Empleador eliminado exitosamente", rs, 200);
        }catch (SQLException err){
            return new ResponseDto("Error al eliminar el empleador", err.getMessage(), 500);
        }
    }


}
