package com.talento_tech.BolsaEmpleo.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.talento_tech.BolsaEmpleo.Controllers.DatabaseConexion;
import com.talento_tech.BolsaEmpleo.Entities.Empleado;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

public class ServiceEmpleado {
    Empleado empleado;

    public ServiceEmpleado() {
        empleado = new Empleado(1, "cv.pdf");
    }

    public ResponseDto getEmpleadosByEmpresaId(long empresaId) {
       String sql = "SELECT * FROM empleados WHERE empresa_id = ?";
        
       try(Connection conn = DatabaseConexion.getConnection();
           PreparedStatement pstmt = conn.prepareStatement(sql)){
           pstmt.setLong(1, empresaId);
           ResultSet rs = pstmt.executeQuery();
           ArrayList<Empleado> empleados = new ArrayList<>();
           while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setEmpleado_id(rs.getLong("empleado_id"));
                empleado.setCv(rs.getString("cv"));
                empleado.setUsuario_id(rs.getLong("user_id"));
                empleados.add(empleado);
           }
           return new ResponseDto("Empleados obtenidos exitosamente", empleados, 200);
       }catch(SQLException e) {
           return new ResponseDto("Error al obtener los empleados", e, 500);
       }
    }

    public ResponseDto getEmpleadoById(long empleadoId) {
        String sql = "SELECT * FROM empleados WHERE empleado_id = ?";
         
        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, empleadoId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setEmpleado_id(empleadoId);
                empleado.setCv(rs.getString("cv"));
                empleado.setUsuario_id(rs.getLong("user_id"));
                return new ResponseDto("Empleado obtenido exitosamente", empleado, 200);
            }else{
                return new ResponseDto("Empleado no encontrado", null, 404);
            }
        }catch(SQLException e) {
            return new ResponseDto("Error al obtener el empleado", e, 500);
        }
     }

    public ResponseDto getEmpleadoByUser(long id) {
        String sql = "SELECT * FROM empleados WHERE user_id = ? ";
         
        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setEmpleado_id(rs.getLong("empleado_id"));
                empleado.setCv(rs.getString("cv"));
                empleado.setUsuario_id(id);
                return new ResponseDto("Empleado obtenido exitosamente", empleado, 200);
            }else{
                return new ResponseDto("Empleado no encontrado", null, 404);
            }
        }catch(SQLException e) {
            return new ResponseDto("Error al obtener el empleado", e, 500);
        }
    }


     public ResponseDto agregar(Empleado empleado) {
        String sql = "INSERT INTO empleados (user_id,cv, habilidades,idiomas) VALUES (?, ?, ?, ?)";
         
        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, empleado.getUsuario_id());
            pstmt.setString(2, empleado.getCv());
            pstmt.setString(3, empleado.getHabilidadesString());
            pstmt.setString(4, empleado.getIdiomasString());

            int filasAfectadas = pstmt.executeUpdate();
            if(filasAfectadas > 0){
                return new ResponseDto("Empleado agregado exitosamente", empleado, 201);
            } else {
                return new ResponseDto("Error al agregar el empleado", null, 400);
            }
        }catch(SQLException e) {
            return new ResponseDto("Error al agregar el empleado", e, 500);
        }
     }

     public ResponseDto eliminar(long empleadoId) {
        String sql = "DELETE FROM empleados WHERE empleado_id = ?";
        Object data = getEmpleadoById(empleadoId).getData();
        if(data == null) {
            return new ResponseDto("Empleado no encontrado", null, 404);
        }
         
        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, empleadoId);
            pstmt.executeUpdate();
            return new ResponseDto("Empleado eliminado exitosamente", data, 200);
        }catch(SQLException e) {
            return new ResponseDto("Error al eliminar el empleado", e, 500);
        }
     }

     public ResponseDto actualizar(Empleado empleado) {
         String sql = "UPDATE empleados SET cv = ?, habilidades = ?, idiomas = ? WHERE empleado_id = ?";
         
         try(Connection conn = DatabaseConexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
             pstmt.setString(1, empleado.getCv());
             pstmt.setString(2, empleado.getHabilidadesString());
             pstmt.setString(3, empleado.getIdiomasString());
             pstmt.setLong(4, empleado.getEmpleado_id());
             int filasAfectadas = pstmt.executeUpdate();
             if(filasAfectadas > 0){
                 return new ResponseDto("Empleado actualizado exitosamente", empleado, 200);
             } else {
                 return new ResponseDto("Empleado no existe", null, 400);
             }
         }catch(SQLException e) {
             return new ResponseDto("Error al actualizar el empleado", e, 500);
         }
     }

    }
