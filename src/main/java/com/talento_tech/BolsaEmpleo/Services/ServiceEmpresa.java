package com.talento_tech.BolsaEmpleo.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.talento_tech.BolsaEmpleo.Controllers.DatabaseConexion;
import com.talento_tech.BolsaEmpleo.Entities.Empleado;
import com.talento_tech.BolsaEmpleo.Entities.Empresa;

public class ServiceEmpresa {
    Empresa empresa;
    ServiceEmpleado serviceEmpleado = new ServiceEmpleado();
    DatabaseConexion databaseConexion = new DatabaseConexion();

    public ServiceEmpresa() {
        
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public ServiceEmpleado getServiceEmpleado() {
        return serviceEmpleado;
    }

    public Empresa getEmpresaById(Long id) {
        String  sql = "SELECT * FROM empresas WHERE empresa_id = ?";
        try(Connection conn = databaseConexion.getConnection();
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

            empresa = new Empresa(nombre,descripcion,logo,telefono,direccion,email,web,tipoEmpresa,nit,representante,identificacionRepresentante,tipoIdentificacionRepresentante);
            empresa.setId(empresaId);
            
            return empresa;
        }
    

        }catch (SQLException e) {
           System.out.println("Error al obtener la empresa: " + e.getMessage());
        }
        return null;
    }
}
