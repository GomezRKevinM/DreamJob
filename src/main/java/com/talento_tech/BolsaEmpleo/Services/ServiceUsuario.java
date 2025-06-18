package com.talento_tech.BolsaEmpleo.Services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.talento_tech.BolsaEmpleo.Controllers.DatabaseConexion;
import com.talento_tech.BolsaEmpleo.Entities.Usuario;
import com.talento_tech.BolsaEmpleo.Entities.tipoID;

public class ServiceUsuario {

    public ArrayList<Usuario> getAllUsers(){
        String sql = "SELECT * FROM usuarios";

        try(Connection connection = DatabaseConexion.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();){

            ArrayList<Usuario> users = new ArrayList<Usuario>();
            while (rs.next()) {
                tipoID tipoID = com.talento_tech.BolsaEmpleo.Entities.tipoID.valueOf(rs.getString("tipoid"));
            
                Usuario user = new Usuario();
                user.setId(rs.getLong("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setIdentificacion(rs.getString("identificacion"));
                user.setEmail(rs.getString("email"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setTelefono(rs.getString("telefono"));
                user.setDireccion(rs.getString("direccion"));
                user.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                user.setIdentificacion(rs.getString("identificacion"));
                user.setTipoID(tipoID);
                user.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                user.setUltimaModificacion(rs.getTimestamp("fecha_ultima_modificacion"));
                users.add(user);
            }
        
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Usuario>();
        }
    }
    
    public void agregarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (username, password, email, nombre, apellido, telefono, direccion, identificacion, tipoid, fecha_nacimiento) VALUES (?, crypt(?, gen_salt('bf')), ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getNombre());
            pstmt.setString(5, usuario.getApellido());
            pstmt.setString(6, usuario.getTelefono());
            pstmt.setString(7, usuario.getDireccion());
            pstmt.setString(8, usuario.getIdentificacion());
            pstmt.setString(9, usuario.getTipoID().name());
            pstmt.setDate(10, usuario.getFechaNacimiento());

            int filasAfectadas = pstmt.executeUpdate();
            if(filasAfectadas > 0){
                System.out.println("Usuario agregado con éxito: " + usuario.getUsername());
            } else {
                System.out.println("Error al agregar el usuario: " + usuario.getUsername());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(Long id) {
        String sql = "DELETE FROM usuarios WHERE user_id = ?";
        
        try (Connection connection = DatabaseConexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario eliminado con éxito: " + id);
            } else {
                System.out.println("No se encontró el usuario con ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET username = ?, password = crypt(?, gen_salt('bf')), email = ?, nombre = ?, apellido = ?, telefono = ?, direccion = ?, identificacion = ?, tipoid = ?, fecha_nacimiento = ? WHERE user_id = ?";
        
        try (Connection connection = DatabaseConexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPassword());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getNombre());
            pstmt.setString(5, usuario.getApellido());
            pstmt.setString(6, usuario.getTelefono());
            pstmt.setString(7, usuario.getDireccion());
            pstmt.setString(8, usuario.getIdentificacion());
            pstmt.setString(9, usuario.getTipoID().name());
            pstmt.setDate(10, usuario.getFechaNacimiento());
            pstmt.setLong(11, usuario.getId());

            int filasAfectadas = pstmt.executeUpdate();
            if(filasAfectadas > 0){
                System.out.println("Usuario editado con éxito: " + usuario.getUsername());
            } else {
                System.out.println("Error al editar el usuario: " + usuario.getUsername());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
