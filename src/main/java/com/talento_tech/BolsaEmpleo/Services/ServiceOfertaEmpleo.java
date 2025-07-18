package com.talento_tech.BolsaEmpleo.Services;

import com.talento_tech.BolsaEmpleo.Controllers.DatabaseConexion;
import com.talento_tech.BolsaEmpleo.Entities.Empleado;
import com.talento_tech.BolsaEmpleo.Entities.Empleador;
import com.talento_tech.BolsaEmpleo.Entities.Empresa;
import com.talento_tech.BolsaEmpleo.Entities.OfertaEmpleo;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceOfertaEmpleo {

    private Empleador empleador;

    public ServiceOfertaEmpleo() {
        empleador = null;
    }

    public ResponseDto agregar(OfertaEmpleo ofertaEmpleo){
        String sql = "INSERT INTO ofertatrabajo (id_empleador, id_empresa, cargo, ubicacion, descripcion, requisitos, salario, fechaexpiracion, tipo_contrato, modalidad, pais, ciudad, departamento, nivel_estudios, experiencia_laboral, idiomas ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,ofertaEmpleo.getEmpleador_id());
            pstmt.setLong(2,ofertaEmpleo.getEmpresa_id());
            pstmt.setString(3,ofertaEmpleo.getCargo());
            pstmt.setString(4,ofertaEmpleo.getUbicacion());
            pstmt.setString(5,ofertaEmpleo.getDescripcion());
            pstmt.setString(6,ofertaEmpleo.getRequisitos());
            pstmt.setDouble(7,ofertaEmpleo.getSalario());
            if(ofertaEmpleo.getFechaExpiracion() == null){pstmt.setDate(8,java.sql.Date.valueOf("2026-01-12"));}
            pstmt.setDate(8,ofertaEmpleo.getFechaExpiracion());
            pstmt.setString(9,ofertaEmpleo.getTipoContrato());
            pstmt.setString(10,ofertaEmpleo.getModalidad());
            pstmt.setString(11,ofertaEmpleo.getPais());
            pstmt.setString(12,ofertaEmpleo.getCiudad());
            pstmt.setString(13,ofertaEmpleo.getDepartamento());
            pstmt.setString(14,ofertaEmpleo.getNivelEstudios());
            pstmt.setString(15,ofertaEmpleo.getExperienciaLaboral());
            pstmt.setString(16,ofertaEmpleo.getIdiomas());

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0){
                return new ResponseDto("Oferta de trabajo agregada exitosamente", ofertaEmpleo, 201);
            }else{
                return new ResponseDto("Error al agregar la oferta de trabajo", null, 400);
            }
        }catch (SQLException error){
            return new ResponseDto("Error al agregar la oferta de trabajo", error.getMessage(), 500);
        }
    }

    public ResponseDto actualizar(OfertaEmpleo ofertaEmpleo){
        String sql = "UPDATE ofertatrabajo SET cargo = ?, ubicacion = ?, descripcion = ?, requisitos = ?, salario = ?, fechaexpiracion = ?, tipo_contrato = ?, modalidad = ?, pais = ?, ciudad = ?, departamento = ?, nivel_estudios = ?, experiencia_laboral = ?, idiomas = ?  WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,ofertaEmpleo.getCargo());
            pstmt.setString(2,ofertaEmpleo.getUbicacion());
            pstmt.setString(3,ofertaEmpleo.getDescripcion());
            pstmt.setString(4,ofertaEmpleo.getRequisitos());
            pstmt.setDouble(5,ofertaEmpleo.getSalario());
            pstmt.setDate(6,ofertaEmpleo.getFechaExpiracion());
            pstmt.setString(7,ofertaEmpleo.getTipoContrato());
            pstmt.setString(8,ofertaEmpleo.getModalidad());
            pstmt.setString(9,ofertaEmpleo.getPais());
            pstmt.setString(10,ofertaEmpleo.getCiudad());
            pstmt.setString(11,ofertaEmpleo.getDepartamento());
            pstmt.setString(12,ofertaEmpleo.getNivelEstudios());
            pstmt.setString(13,ofertaEmpleo.getExperienciaLaboral());
            pstmt.setString(14,ofertaEmpleo.getIdiomas());
            pstmt.setLong(15,ofertaEmpleo.getId());

            int filasAfectadas = pstmt.executeUpdate();

            if(filasAfectadas > 0){
                return new ResponseDto("Oferta de trabajo actualizada exitosamente", ofertaEmpleo, 200);
            }else{
                return new ResponseDto("Error al actualizar la oferta de trabajo", filasAfectadas, 400);
            }
        }catch (SQLException error){
            return new ResponseDto("Error al actualizar la oferta de trabajo", error.getMessage(), 500);
        }
    }

    public ResponseDto eliminar(Long id){
        String sql = "DELETE FROM ofertatrabajo WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,id);

            int filasAfectadas = pstmt.executeUpdate();

            if(filasAfectadas > 0){
                return new ResponseDto("Oferta de empleo eliminada exitosamente",filasAfectadas,200);
            }else{
                return new ResponseDto("No se pudo eliminar la oferta",pstmt.getMetaData(),404);
            }
        }catch (SQLException error){
            return new ResponseDto("Error al eliminar oferta de empleo",error.getMessage(), 500);
        }
    }

    public ResponseDto lista(){
        String sql = "SELECT * FROM ofertatrabajo INNER join empresas ON id_empresa = empresa_id";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();){
            
            ArrayList<OfertaEmpleo> lista = new ArrayList<OfertaEmpleo>();

            while(rs.next()){
                OfertaEmpleo recibida = new OfertaEmpleo();
                recibida.setId(rs.getLong("id"));
                recibida.setEmpresa_id(rs.getLong("id_empresa"));
                recibida.setEmpleador_id(rs.getLong("id_empleador"));
                recibida.setCargo(rs.getString("cargo"));
                recibida.setDescripcion(rs.getString("descripcion"));
                recibida.setRequisitos(rs.getString("requisitos"));
                recibida.setSalario(rs.getDouble("salario"));
                recibida.setFechaExpiracion(rs.getDate("fechaexpiracion"));
                recibida.setTipoContrato(rs.getString("tipo_contrato"));
                recibida.setModalidad(rs.getString("modalidad"));
                recibida.setPais(rs.getString("pais"));
                recibida.setCiudad(rs.getString("ciudad"));
                recibida.setDepartamento(rs.getString("departamento"));
                recibida.setNivelEstudios(rs.getString("nivel_estudios"));
                recibida.setExperienciaLaboral(rs.getString("experiencia_laboral"));
                recibida.setIdiomas(rs.getString("idiomas"));
                recibida.setFechaPublicacion(rs.getTimestamp("fecha_publicacion"));
                recibida.setUbicacion(rs.getString("ubicacion"));
                recibida.setNombreEmpresa(rs.getString("nombre"));


                lista.add(recibida);
            }
            return new ResponseDto("Lista de ofertas obtenida",lista,200);
        }catch(SQLException error){
            return new ResponseDto("Error al obtener las ofertas de empleo",error.getMessage(), 500);
        }
    }

    public ResponseDto contar(){
        String sql = "SELECT COUNT(*) FROM ofertatrabajo";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return new ResponseDto("Cantidad de ofertas", count, 200);
            } else {
                return new ResponseDto("No se pudo obtener el conteo", null, 500);
            }
        }catch(SQLException error){
            return new ResponseDto("Error al contar ofertas de trabajo",error.getMessage(), 500);
        }
    }

    public ResponseDto getOferta(Long id){
        String sql = "SELECT * FROM ofertatrabajo WHERE id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1,id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                OfertaEmpleo recibida = new OfertaEmpleo();
                recibida.setId(id);
                recibida.setEmpresa_id(rs.getLong("id_empresa"));
                recibida.setEmpleador_id(rs.getLong("id_empleador"));
                recibida.setCargo(rs.getString("cargo"));
                recibida.setDescripcion(rs.getString("descripcion"));
                recibida.setRequisitos(rs.getString("requisitos"));
                recibida.setSalario(rs.getDouble("salario"));
                recibida.setFechaExpiracion(rs.getDate("fechaexpiracion"));
                recibida.setTipoContrato(rs.getString("tipo_contrato"));
                recibida.setModalidad(rs.getString("modalidad"));
                recibida.setPais(rs.getString("pais"));
                recibida.setCiudad(rs.getString("ciudad"));
                recibida.setDepartamento(rs.getString("departamento"));
                recibida.setNivelEstudios(rs.getString("nivel_estudios"));
                recibida.setExperienciaLaboral(rs.getString("experiencia_laboral"));
                recibida.setIdiomas(rs.getString("idiomas"));
                recibida.setFechaPublicacion(rs.getTimestamp("fecha_publicacion"));
                recibida.setUbicacion(rs.getString("ubicacion"));

                return new ResponseDto("Oferta encontrada con el id "+id,recibida,200);
            }else {
                return new ResponseDto("No se encontro la oferta con el id"+id,null,404);
            }

        }catch (SQLException error){
            return new ResponseDto("Error al obtener oferta",error.getMessage(), 500);
        }
    }
}
