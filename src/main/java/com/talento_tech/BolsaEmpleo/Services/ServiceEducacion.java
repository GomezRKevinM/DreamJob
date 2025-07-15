package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Controllers.DatabaseConexion;
import com.talento_tech.BolsaEmpleo.Entities.Educacion;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceEducacion {

    public ResponseDto getEducacion(Long id) {
        String sql = "SELECT * FROM educacion WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Educacion educacion = new Educacion(
                        id,
                        rs.getString("institucion"),
                        rs.getString("tipo_instituto"),
                        rs.getString("titulo"),
                        rs.getDate("inicio"),
                        rs.getBoolean("cursando_actualmente"),
                        rs.getString("pais"),
                        rs.getString("departamento"),
                        rs.getString("ciudad"),
                        rs.getLong("empleado_id") );
                educacion.setFecha_fin(rs.getDate("termino"));

                return new ResponseDto("Educación encontrada",educacion,200);
            }else{
                return new ResponseDto("No se encontro educación con el #"+id,null,404);
            }

        }catch (SQLException error){
            return  new ResponseDto("Error al obtener educación #"+id,error.getMessage(),500);
        }
    }

    public ResponseDto getEducacionByEmpleadoId(Long id){
        String sql = "SELECT * FROM educacion WHERE empleado_id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            ArrayList<Educacion> educaciones = new ArrayList<Educacion>();
            while(rs.next()) {
                Educacion educacion = new Educacion(
                        id,
                        rs.getString("institucion"),
                        rs.getString("tipo_instituto"),
                        rs.getString("titulo"),
                        rs.getDate("inicio"),
                        rs.getBoolean("cursando_actualmente"),
                        rs.getString("pais"),
                        rs.getString("departamento"),
                        rs.getString("ciudad"),
                        id );
                educacion.setFecha_fin(rs.getDate("termino"));

                educaciones.add(educacion);
            }
            return new ResponseDto("Lista de educación obtenida",educaciones,200);
        }catch (SQLException error){
            return new ResponseDto("Error al obtener educación del usuario",error.getMessage(),500);
        }
    }

    public ResponseDto agregar(Educacion educacion){
        String sql = "INSERT INTO educacion (institucion, tipo_instituto, titulo, inicio, termino, cursando_actualmente, pais, departamento, ciudad, empleado_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, educacion.getInstitucion());
            ps.setString(2,educacion.getTipo_institucion());
            ps.setString(3, educacion.getTitulo());
            ps.setDate(4,educacion.getFecha_inicio());
            ps.setDate(5,educacion.getFecha_fin());
            ps.setBoolean(6,educacion.getCursando_actualmente());
            ps.setString(7,educacion.getPais());
            ps.setString(8,educacion.getDepartamento());
            ps.setString(9,educacion.getCiudad());
            ps.setLong(10,educacion.getEmpleado_id());

            int filas = ps.executeUpdate();

            if(filas > 0){
                return new ResponseDto("Educación agregada exitosamente",educacion,200);
            }else{
                return new ResponseDto("No se pudo agregar la educación",null,400);
            }
        }catch(SQLException error){
            return new ResponseDto("Error al agregar el educacion",error.getMessage(),500);
        }
    }

    public ResponseDto actualizar(Educacion educacion){
        String sql = "UPDATE educacion SET institucion = ?, tipo_instituto = ?, titulo = ?, inicio = ?, termino = ?, cursando_actualmente = ?, pais = ?, departamento = ?, ciudad = ? WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, educacion.getInstitucion());
            ps.setString(2, educacion.getTipo_institucion());
            ps.setString(3, educacion.getTitulo());
            ps.setDate(4, educacion.getFecha_inicio());
            ps.setDate(5, educacion.getFecha_fin());
            ps.setBoolean(6, educacion.getCursando_actualmente());
            ps.setString(7, educacion.getPais());
            ps.setString(8, educacion.getDepartamento());
            ps.setString(9, educacion.getCiudad());
            ps.setLong(10,educacion.getId());

            int filas = ps.executeUpdate();

            if(filas > 0){
                return new ResponseDto("Datos de educación actualizados exitosamente",educacion,200);
            }else{
                return new ResponseDto("No se pudo actualizar los datos de la educación",null,400);
            }

        }catch(SQLException error){
            return new ResponseDto("Error al actualizar educación",error.getMessage(),500);
        }
    }

    public ResponseDto eliminar(Long id){
        String sql = "DELETE FROM educacion WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);

            int filas = ps.executeUpdate();

            if(filas > 0){
                return new ResponseDto("Educación eliminada exitosamente",id,200);
            }else{
                return new ResponseDto("No se pudo eliminar la educación",null,400);
            }

        }catch (SQLException error){
            return new ResponseDto("Error al eliminar datos",error.getMessage(),500);
        }
    }
}
