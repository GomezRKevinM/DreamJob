package com.talento_tech.BolsaEmpleo.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.talento_tech.BolsaEmpleo.Controllers.DatabaseConexion;
import com.talento_tech.BolsaEmpleo.Entities.Empresa;
import com.talento_tech.BolsaEmpleo.dto.ResponseDto;

public class ServiceEmpresa {
    Empresa empresa;
    ServiceEmpleado serviceEmpleado = new ServiceEmpleado();
    

    public ServiceEmpresa() {
        
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public ServiceEmpleado getServiceEmpleado() {
        return serviceEmpleado;
    }

    public ResponseDto getEmpresaById(Long id) {
        Empresa empresaEncontrda;
        String  sql = "SELECT * FROM empresas WHERE empresa_id = ?";
        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, id);
                ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
            long empresaId = rs.getLong("empresa_id");
            String nombre = rs.getString("nombre");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");
            String direccion = rs.getString("direccion");
            String descripcion = rs.getString("descripcion");
            String logo = rs.getString("logo");
            String web = rs.getString("web");
            String tipoEmpresa = rs.getString("tipo_empresa");
            String nit = rs.getString("nit");
            String representante = rs.getString("representante");
            String identificacionRepresentante = rs.getString("representante_identificacion");
            String tipoIdentificacionRepresentante = rs.getString("representante_tipo_identificacion");

            empresaEncontrda = new Empresa(nombre,descripcion,logo,telefono,direccion,email,web,tipoEmpresa,nit,representante,identificacionRepresentante,tipoIdentificacionRepresentante);
            empresaEncontrda.setId(empresaId);
            ResponseDto response = new ResponseDto("Empresa encontrada", empresaEncontrda, 200);
            return response;
        }else{
            ResponseDto response = new ResponseDto("Empresa no encontrada", null, 404);
            return response;
        }
    

        }catch (SQLException e) {
           System.out.println("Error al obtener la empresa: " + e.getMessage());
        }
        return null;
    }

    public ResponseDto crearEmpresa(Empresa newEmpresa)throws SQLException {
        String sql = "INSERT INTO empresas (nombre,descripcion,logo,telefono,direccion,email,web,tipo_empresa,nit,representante,representante_identificacion,representante_tipo_identificacion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, newEmpresa.getNombre());
            pstmt.setString(2, newEmpresa.getDescripcion());
            pstmt.setString(3, newEmpresa.getLogo());
            pstmt.setString(4, newEmpresa.getTelefono());
            pstmt.setString(5, newEmpresa.getDireccion());
            pstmt.setString(6, newEmpresa.getEmail());
            pstmt.setString(7, newEmpresa.getWeb());
            pstmt.setString(8, newEmpresa.getTipoEmpresa());
            pstmt.setString(9, newEmpresa.getNIT());
            pstmt.setString(10, newEmpresa.getRepresentante());
            pstmt.setString(11, newEmpresa.getIdentificacionRepresentante());
            pstmt.setString(12, newEmpresa.getTipoIdentificacionRepresentante());
            pstmt.executeUpdate();
            System.out.println("Empresa creada exitosamente");
            ResponseDto response = new ResponseDto("Empresa creada exitosamente", newEmpresa, 201);
            return response;
        }catch(SQLException e) {
            ResponseDto response = new ResponseDto("Error al crear la empresa", e, 400);
            return response;
        }
    }

    public ResponseDto editarEmpresa(Empresa empresa) throws SQLException {
        String sql = "UPDATE empresas SET nombre = ?, descripción = ?, logo = ?, telefono = ?, direccion = ?, email = ?, web = ?, tipo_empresa = ?, nit = ?, representante = ?, representante_identificacion = ?, representante_tipo_identificacion = ? WHERE empresa_id = ?";

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, empresa.getNombre());
            pstmt.setString(2, empresa.getDescripcion());
            pstmt.setString(3, empresa.getLogo());
            pstmt.setString(4, empresa.getTelefono());
            pstmt.setString(5, empresa.getDireccion());
            pstmt.setString(6, empresa.getEmail());
            pstmt.setString(7, empresa.getWeb());
            pstmt.setString(8, empresa.getTipoEmpresa());
            pstmt.setString(9, empresa.getNIT());
            pstmt.setString(10, empresa.getRepresentante());
            pstmt.setString(11, empresa.getIdentificacionRepresentante());
            pstmt.setString(12, empresa.getTipoIdentificacionRepresentante());
            pstmt.setLong(13, empresa.getId());
            pstmt.executeUpdate();
            System.out.println("Empresa editada exitosamente");
            
            return new ResponseDto("Empresa editada exitosamente", empresa, 201);
        }catch(SQLException e) {
            return new ResponseDto("Error al editar la empresa", e, 400);
        }
    }

    public ResponseDto eliminarEmpresa(Long empresaId) throws SQLException {
        Empresa empresa = Empresa.class.cast(getEmpresaById(empresaId).getData());
        String sql = "DELETE FROM empresas WHERE empresa_id = ?";    

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setLong(1, empresaId);
            pstmt.executeUpdate();
            System.out.println("Empresa eliminada exitosamente");
            return new ResponseDto("Empresa eliminada exitosamente", empresa, 200);
        }catch(SQLException e) {
            return new ResponseDto("Error al eliminar la empresa", e, 400);
        }
    }

    public ResponseDto getEmpresas() {
        String sql = "SELECT * FROM empresas";
        ArrayList<Empresa> empresas = new ArrayList<>();

        try(Connection conn = DatabaseConexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                long empresaId = rs.getLong("empresa_id");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                String descripcion = rs.getString("descripcion");
                String logo = rs.getString("logo");
                String web = rs.getString("web");
                String tipoEmpresa = rs.getString("tipo_empresa");
                String nit = rs.getString("nit");
                String representante = rs.getString("representante");
                String identificacionRepresentante = rs.getString("representante_identificacion");
                String tipoIdentificacionRepresentante = rs.getString("representante_tipo_identificacion");

                Empresa empresa = new Empresa(nombre,descripcion,logo,telefono,direccion,email,web,tipoEmpresa,nit,representante,identificacionRepresentante,tipoIdentificacionRepresentante);
                empresa.setId(empresaId);
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            return new ResponseDto("Error al obtener las empresas", e, 401);
        }
        return new ResponseDto("Empresas obtenidas exitosamente", empresas, 200);
    }
}
