package com.talento_tech.BolsaEmpleo.Services;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.talento_tech.BolsaEmpleo.Controllers.DatabaseConexion;
import com.talento_tech.BolsaEmpleo.Entities.Aplicacion;
import com.talento_tech.BolsaEmpleo.Entities.UserSesion;
import com.talento_tech.BolsaEmpleo.Entities.Usuario;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

public class ServiceAplicacion {
    
    public ResponseDto aplicar(Long oferta_id, Long empleado_id) {
        String sql = "INSERT INTO aplicaciones (oferta_id,empleado_id,estado) VALUES (?,?,'Aplicado')";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,oferta_id);
            pstmt.setLong(2,empleado_id);
            int rows =pstmt.executeUpdate();

            if(rows > 0){
                return new ResponseDto("usuario aplicado a la Oferta de Empleo exitosamente",rows,200);
            }else{
                return new ResponseDto("Error al aplicar oferta",null,500);
            }
        }catch (SQLException error){
            return new ResponseDto("Error al aplicar oferta",error.getMessage(), 500);
        }
    }

    public ResponseDto update(Long id,String estado,String comentario){
        String sql = "UPDATE aplicaciones SET estado = ? WHERE id = ?";
        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,estado);
            pstmt.setLong(2,id);
            pstmt.setString(3,comentario);
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return new ResponseDto("Aplicacion actualizada exitosamente",rows,200);
            }else{
                return new ResponseDto("Error al actualizar la aplicacion",null,500);
            }
        }catch (SQLException error){
            return new ResponseDto("Error al actualizar la aplicacion",error.getMessage(), 500);
        }
    }

    public ResponseDto eliminar(Long id){
        String sql = "DELETE FROM aplicaciones WHERE id = ?";
        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,id);
            int rows = pstmt.executeUpdate();
            if(rows > 0){
                return new ResponseDto("Aplicacion eliminada exitosamente",rows,200);
            }else{
                return new ResponseDto("Error al eliminar la aplicacion",null,500);
            }
        }catch (SQLException error){
            return new ResponseDto("Error al eliminar la aplicacion",error.getMessage(), 500);
        }
    }

    public ResponseDto getByOferta(Long id){
        String sql = "SELECT * FROM aplicaciones WHERE oferta_id = ? ";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,id);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Aplicacion> aplicaciones = new ArrayList<Aplicacion>();
            while(rs.next()){
                Aplicacion aplicacion = new Aplicacion();
                aplicacion.setId(rs.getLong("id"));
                aplicacion.setIdOfertaEmpleo(rs.getLong("oferta_id"));
                aplicacion.setIdEmpleado(rs.getLong("empleado_id"));
                aplicacion.setEstado(rs.getString("estado"));
                aplicacion.setComentario(rs.getString("comentario"));
                aplicaciones.add(aplicacion);
            }
            return new ResponseDto("Aplicaciones obtenidas exitosamente",aplicaciones,200);
        }catch (SQLException error){
            return new ResponseDto("Error al obtener las aplicaciones",error.getMessage(), 500);
        }
    }

    public ResponseDto get(Long id){
        String sql = "SELECT * FROM aplicaciones WHERE id = ? ";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                Aplicacion aplicacion = new Aplicacion();
                aplicacion.setId(rs.getLong("id"));
                aplicacion.setIdOfertaEmpleo(rs.getLong("oferta_id"));
                aplicacion.setIdEmpleado(rs.getLong("empleado_id"));
                aplicacion.setEstado(rs.getString("estado"));
                aplicacion.setComentario(rs.getString("comentario"));
                return new ResponseDto("Aplicacion obtenida exitosamente",aplicacion,200);
            }else{
                return new ResponseDto("No se encontro la aplicacion #"+id,null,404);
            }
        }catch (SQLException error){
            return new ResponseDto("Error al obtener la aplicacion",error.getMessage(), 500);
        }
    }

}
