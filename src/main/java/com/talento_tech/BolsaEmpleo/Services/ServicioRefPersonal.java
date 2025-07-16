package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Controllers.DatabaseConexion;
import com.talento_tech.BolsaEmpleo.Entities.ReferenciaPersonal;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServicioRefPersonal {

    public ResponseDto agregar(ReferenciaPersonal referencia){
        String sql = "INSERT INTO refpersonal (nombre,telefono,parentezco,empleado_id) VALUES (?,?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, referencia.getNombre());
            ps.setString(2, referencia.getTelefono());
            ps.setString(3, referencia.getParentezco());
            ps.setLong(4, referencia.getEmpleado_id());

            int filas = ps.executeUpdate();

            if(filas > 0){
                return new ResponseDto("Referencia personal agregada exitosamente",filas,201);
            }else{
                return new ResponseDto("No se pudo agregar la referencia personal",ps.getMetaData(),404);
            }
        }catch (SQLException error){
            return new ResponseDto("Error al agregar referencia personal",error.getMessage(),500);
        }
    }

    public ResponseDto actualizar(ReferenciaPersonal referencia){
        String sql = "UPDATE refpersonal SET nombre = ?, telefono = ?, parentezco = ? WHERE empleado_id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, referencia.getNombre());
            ps.setString(2, referencia.getTelefono());
            ps.setString(3, referencia.getParentezco());
            ps.setLong(4, referencia.getEmpleado_id());

            int filas = ps.executeUpdate();

            if(filas > 0){
                return new ResponseDto("Referencia personal actualizada exitosamente",referencia,200);
            }else{
                return new ResponseDto("No se pudo actualizar la referencia personal",null,404);
            }
        }catch(SQLException error){
            return new ResponseDto("Error al actualizar referencia",error.getMessage(),500);
        }
    }

    public ResponseDto eliminar(Long id){
        String sql = "DELETE refpersonal WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, id);

            int filas = ps.executeUpdate();

            if(filas > 0){
                return new ResponseDto("Referencia personal eliminada exitosamente",filas,200);
            }else{
                return new ResponseDto("No se pudo eliminar la referencia",null,404);
            }
        }catch(SQLException error){
            return new ResponseDto("Error al eliminar referencia",error.getMessage(),500);
        }
    }

    public ResponseDto getReferencia(Long id){
        String sql = "SELECT * FROM refpersonal WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                ReferenciaPersonal referencia = new ReferenciaPersonal(id,rs.getString("nombre"),rs.getString("telefono"),rs.getString("parentezco"), rs.getLong("empleado_id"));
                return new ResponseDto("Referencia personal obtenida exitosamente",referencia,200);
            }else{
                return new ResponseDto("No se encontro la referencia #"+id,null,404);
            }
        }catch(SQLException error){
            return new ResponseDto("Error al obtener la referencia",error.getMessage(),500);
        }
    }

    public ResponseDto getReferenciasByEmpleado(Long id){
        String sql = "SELECT * FROM refpersonal WHERE empleado_id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();

            ArrayList<ReferenciaPersonal> referencias = new ArrayList<ReferenciaPersonal>();
            while(rs.next()){
                ReferenciaPersonal referencia = new ReferenciaPersonal(id,rs.getString("nombre"),rs.getString("telefono"),rs.getString("parentezco"), rs.getLong("empleado_id"));
                referencias.add(referencia);
            }
            return new ResponseDto("Referencias del empleado #"+id+" obtenidas",referencias,200);
        }catch (SQLException error){
            return new ResponseDto("Error al obtener la referencia",error.getMessage(),500);
        }
    }

}
